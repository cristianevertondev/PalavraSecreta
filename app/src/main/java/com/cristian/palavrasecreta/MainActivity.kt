package com.cristian.palavrasecreta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cristian.palavrasecreta.audio.AudioLifecycleObserver
import com.cristian.palavrasecreta.audio.AudioManager
import com.cristian.palavrasecreta.ui.PalavraSecretaApp
import com.cristian.palavrasecreta.ui.theme.PalavraSecretaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Áudio: inicializa a instância única e acompanha o ciclo de vida.
        AudioManager.init(applicationContext)
        lifecycle.addObserver(AudioLifecycleObserver())
        enableEdgeToEdge()
        setContent {
            PalavraSecretaTheme {
                PalavraSecretaApp()
            }
        }
    }
}