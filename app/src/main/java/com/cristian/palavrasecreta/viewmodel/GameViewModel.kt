package com.cristian.palavrasecreta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.palavrasecreta.audio.AudioManager
import com.cristian.palavrasecreta.data.GameLogic
import com.cristian.palavrasecreta.data.content.GameContent
import com.cristian.palavrasecreta.data.model.Chapter
import com.cristian.palavrasecreta.data.model.Level
import com.cristian.palavrasecreta.data.progress.ProgressRepository
import com.cristian.palavrasecreta.data.settings.AudioSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Tipo de feedback exibido durante uma fase.
 */
enum class FeedbackType { CORRECT, WRONG, OUT_OF_ATTEMPTS }

/**
 * Estado da tela de jogo para o nível em andamento.
 */
data class GameUiState(
    val level: Level? = null,
    val input: String = "",
    val revealedHints: Int = 1,
    val attemptsUsed: Int = 0,
    val feedback: Pair<FeedbackType, String>? = null,
    val complete: Boolean = false,
    val outOfAttempts: Boolean = false
)

/**
 * Telas possíveis do app. Home é a raiz da pilha de navegação.
 */
sealed class Screen {
    object Home : Screen()
    data class Chapters(val fromGame: Boolean = false) : Screen()
    data class LevelMap(val chapterId: Int) : Screen()
    data class Game(val chapterId: Int, val levelId: Int) : Screen()
}

/**
 * ViewModel central: conteúdo, progresso persistido, navegação e lógica da fase.
 * Toda a lógica de negócio fica aqui, mantendo os Composables enxutos.
 */
class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val progressRepository = ProgressRepository(application)

    private val _completedLevels = MutableStateFlow<Set<Int>>(emptySet())
    val completedLevels: StateFlow<Set<Int>> = _completedLevels.asStateFlow()

    private val _navStack = MutableStateFlow<List<Screen>>(listOf(Screen.Home))
    val navStack: StateFlow<List<Screen>> = _navStack.asStateFlow()

    private val _gameState = MutableStateFlow(GameUiState())
    val gameState: StateFlow<GameUiState> = _gameState.asStateFlow()

    val chapters: List<Chapter> = GameContent.chapters

    // ---------------------------------------------------------------- áudio

    private val audio = AudioManager.get()
    val audioSettings: StateFlow<AudioSettings> = audio.settings

    fun setMusicOn(on: Boolean) = audio.setMusicOn(on)
    fun setSfxOn(on: Boolean) = audio.setSfxOn(on)

    init {
        viewModelScope.launch {
            progressRepository.completedLevelIds.collect { ids ->
                _completedLevels.value = ids
            }
        }
    }

    // ---------------------------------------------------------------- navegação

    val currentScreen: Screen
        get() = _navStack.value.last()

    fun navigate(screen: Screen) {
        audio.playClick()
        _navStack.update { it + screen }
    }

    fun goBack() {
        audio.playClick()
        _navStack.update { stack ->
            if (stack.size > 1) stack.dropLast(1) else stack
        }
    }

    fun goHome() {
        _navStack.value = listOf(Screen.Home)
    }

    fun openChapters(fromGame: Boolean = false) = navigate(Screen.Chapters(fromGame))

    fun openLevelMap(chapterId: Int) = navigate(Screen.LevelMap(chapterId))

    fun startLevel(chapterId: Int, levelId: Int) {
        val level = GameContent.levelById(levelId) ?: return
        audio.playClick()
        _gameState.value = GameUiState(level = level, revealedHints = 1)
        // Substitui o topo por Game para que "próximo nível" não acumule pilha.
        _navStack.update { stack ->
            if (stack.lastOrNull() is Screen.Game) stack.dropLast(1) + Screen.Game(chapterId, levelId)
            else stack + Screen.Game(chapterId, levelId)
        }
    }

    // ---------------------------------------------------------------- progresso

    private fun isLevelUnlocked(levelId: Int, completed: Set<Int>): Boolean =
        GameLogic.isLevelUnlocked(GameContent.allLevels, completed, levelId)

    fun isLevelUnlocked(levelId: Int): Boolean = isLevelUnlocked(levelId, _completedLevels.value)

    fun isChapterUnlocked(chapter: Chapter): Boolean =
        GameLogic.isChapterUnlocked(chapters, _completedLevels.value, chapter.id)

    fun chapterCompleted(completed: Set<Int>, chapter: Chapter): Boolean =
        chapter.levels.isNotEmpty() && chapter.levels.all { it.id in completed }

    fun isLevelCompleted(levelId: Int): Boolean = levelId in _completedLevels.value

    /** O primeiro capítulo jogável que ainda tem nível a ser jogado (para o botão JOGAR). */
    fun currentChapterForContinue(): Chapter? {
        val completed = _completedLevels.value
        return chapters.firstOrNull { chapter -> chapter.levels.any { it.id !in completed } }
            ?: chapters.lastOrNull { it.isPlayable }
    }

    // ---------------------------------------------------------------- fase (lógica)

    fun onInputChange(value: String) {
        _gameState.update { it.copy(input = value.take(24), feedback = null) }
    }

    fun onSubmit() {
        val state = _gameState.value
        val level = state.level ?: return
        if (state.complete || state.outOfAttempts) return
        val typed = state.input.trim()
        if (typed.isEmpty()) return

        if (GameLogic.normalizeAnswer(typed) == GameLogic.normalizeAnswer(level.word)) {
            _gameState.update { it.copy(complete = true, feedback = FeedbackType.CORRECT to "CORRETO!") }
            audio.playCorrect()
            viewModelScope.launch {
                progressRepository.markLevelCompleted(level.id)
                _completedLevels.update { it + level.id }
                // Após concluir, celebra a progressão: conclusão do capítulo ou novo desbloqueio.
                val completed = _completedLevels.value
                val chapter = GameContent.chapterById(level.chapterId)
                val chapterDone = chapter != null && chapter.isPlayable && chapter.levels.all { it.id in completed }
                if (chapterDone) audio.playChapterComplete() else audio.playUnlock()
            }
        } else {
            val newAttempts = state.attemptsUsed + 1
            if (newAttempts >= level.maxAttempts) {
                audio.playWrong()
                _gameState.update {
                    it.copy(
                        attemptsUsed = newAttempts,
                        revealedHints = level.hints.size,
                        feedback = FeedbackType.OUT_OF_ATTEMPTS to "Tentativas esgotadas. A palavra era ${level.word}.",
                        outOfAttempts = true
                    )
                }
            } else {
                val revealingHint = state.revealedHints < level.hints.size
                _gameState.update {
                    it.copy(
                        attemptsUsed = newAttempts,
                        revealedHints = (it.revealedHints + 1).coerceAtMost(level.hints.size),
                        feedback = FeedbackType.WRONG to "Não foi dessa vez. Tente novamente!"
                    )
                }
                audio.playWrong()
                if (revealingHint) audio.playHint()
            }
        }
    }

    /** Reinicia a fase após esgotar tentativas, para o jogador tentar de novo. */
    fun retryLevel() {
        val state = _gameState.value
        if (state.level != null) {
            _gameState.value = GameUiState(level = state.level, revealedHints = 1)
        }
    }

    /** Avança para o próximo nível não concluído; se for o fim, volta ao mapa. */
    fun onNextLevel() {
        val state = _gameState.value
        val level = state.level ?: return
        val levels = GameContent.allLevels
        val index = levels.indexOfFirst { it.id == level.id }
        val next = if (index in 0 until levels.lastIndex) levels[index + 1] else null
        if (next != null) {
            startLevel(next.chapterId, next.id)
        } else {
            _navStack.update { stack -> stack.dropLast(1) }
        }
    }
}