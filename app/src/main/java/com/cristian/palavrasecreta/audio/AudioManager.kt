package com.cristian.palavrasecreta.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import com.cristian.palavrasecreta.data.settings.AudioSettings
import com.cristian.palavrasecreta.data.settings.AudioSettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Componente centralizado de áudio do jogo.
 *
 * - Uma única instância durante toda a execução (singleton).
 * - Música de fundo: um único [MediaPlayer] em loop infinito, sem reiniciar
 *   entre telas ou em recomposições.
 * - Efeitos sonoros curtos: [SoundPool].
 *
 * Os arquivos de áudio são resolvidos por nome na pasta `res/raw`. Se um
 * recurso ainda não existir (ex.: durante o desenvolvimento), a chamada é um
 * no-op silencioso — o restante do app continua funcionando normalmente.
 *
 * O AudioManager NÃO conhece regras de fases/capítulos: apenas reproduz áudio.
 */
class AudioManager private constructor(context: Context) {

    companion object {
        @Volatile
        private var instance: AudioManager? = null

        /** Inicializa a única instância (chamado uma vez, com o app). */
        fun init(context: Context) {
            synchronized(this) {
                if (instance == null) {
                    instance = AudioManager(context.applicationContext)
                }
            }
        }

        fun get(): AudioManager =
            instance ?: throw IllegalStateException("AudioManager não inicializado. Chame AudioManager.init(context).")
    }

    private val appContext = context.applicationContext
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val settingsRepository = AudioSettingsRepository(appContext)

    private val _settings = MutableStateFlow(AudioSettings())
    val settings: StateFlow<AudioSettings> = _settings.asStateFlow()

    private var musicPlayer: MediaPlayer? = null
    private var soundPool: SoundPool? = null
    private val loadedSounds = HashMap<Int, Int>() // raw resId -> soundId

    // Música de fundo sutil; efeitos um pouco mais perceptíveis.
    private val MUSIC_VOLUME = 0.28f
    private val SFX_VOLUME = 0.7f

    init {
        scope.launch {
            settingsRepository.settings.collect { value ->
                _settings.value = value
                applyMusicState()
            }
        }
    }

    // ---------------------------------------------------------------- recursos raw

    private fun rawResourceId(name: String): Int =
        appContext.resources.getIdentifier(name, "raw", appContext.packageName)

    // ---------------------------------------------------------------- música

    private fun applyMusicState() {
        if (_settings.value.musicOn) playMusic() else pauseMusic()
    }

    fun playMusic() {
        if (!_settings.value.musicOn) return
        val resId = rawResourceId("gameplay_music")
        if (resId == 0) return
        if (musicPlayer == null) {
            val player = MediaPlayer.create(appContext, resId) ?: return
            player.isLooping = true
            player.setVolume(MUSIC_VOLUME, MUSIC_VOLUME)
            musicPlayer = player
        }
        val player = musicPlayer ?: return
        if (!player.isPlaying) player.start()
    }

    fun pauseMusic() {
        musicPlayer?.takeIf { it.isPlaying }?.pause()
    }

    fun resumeMusic() = playMusic()

    fun stopMusic() {
        musicPlayer?.let { player ->
            if (player.isPlaying) player.pause()
            player.seekTo(0)
        }
    }

    fun setMusicVolume(volume: Float) {
        musicPlayer?.setVolume(volume, volume)
    }

    // ---------------------------------------------------------------- efeitos

    private fun ensureSoundPool(): SoundPool? {
        if (soundPool == null) {
            soundPool = SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .build()
        }
        return soundPool
    }

    private fun playEffect(name: String) {
        if (!_settings.value.sfxOn) return
        val pool = ensureSoundPool() ?: return
        val resId = rawResourceId(name)
        if (resId == 0) return
        val soundId = loadedSounds.getOrPut(resId) { pool.load(appContext, resId, 1) }
        if (soundId != 0) {
            pool.play(soundId, SFX_VOLUME, SFX_VOLUME, 1, 0, 1f)
        }
    }

    fun playClick() = playEffect("sfx_click")
    fun playCorrect() = playEffect("sfx_correct")
    fun playWrong() = playEffect("sfx_wrong")
    fun playHint() = playEffect("sfx_hint")
    fun playUnlock() = playEffect("sfx_unlock")
    fun playChapterComplete() = playEffect("sfx_chapter_complete")

    // ---------------------------------------------------------------- preferências

    fun setMusicOn(on: Boolean) {
        scope.launch { settingsRepository.setMusicOn(on) }
    }

    fun setSfxOn(on: Boolean) {
        scope.launch { settingsRepository.setSfxOn(on) }
    }

    // ---------------------------------------------------------------- ciclo de vida

    fun onAppBackgrounded() = pauseMusic()

    fun onAppForegrounded() = applyMusicState()

    fun release() {
        musicPlayer?.release()
        musicPlayer = null
        soundPool?.release()
        soundPool = null
        loadedSounds.clear()
    }
}