package com.cristian.palavrasecreta.audio

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Observa o ciclo de vida da Activity para pausar a música quando o app vai
 * para segundo plano e retomá-la ao voltar, respeitando a preferência do jogador.
 */
class AudioLifecycleObserver : DefaultLifecycleObserver {

    override fun onStop(owner: LifecycleOwner) {
        AudioManager.get().onAppBackgrounded()
    }

    override fun onStart(owner: LifecycleOwner) {
        AudioManager.get().onAppForegrounded()
    }
}