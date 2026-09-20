package com.cristian.palavrasecreta

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * Smoke test instrumentado: o app abre na Home, navega até os capítulos,
 * entra no mapa de níveis e abre uma fase. Roda no emulador/dispositivo.
 */
class GameAppInstrumentedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun homeScreenShowsTitleAndPlayButton() {
        composeRule.onNodeWithText("PALAVRA").assertIsDisplayed()
        composeRule.onNodeWithText("SECRETA").assertIsDisplayed()
        composeRule.onNodeWithText("JOGAR", substring = true).assertIsDisplayed()
        composeRule.onNodeWithText("CAPÍTULOS").assertIsDisplayed()
    }

    @Test
    fun navigateToChaptersThenMapThenOpenLevel() {
        // Home → Capítulos
        composeRule.onNodeWithText("CAPÍTULOS").performClick()
        composeRule.onNodeWithText("Capítulos").assertIsDisplayed()
        composeRule.onNodeWithText("Reino das Frutas").assertIsDisplayed()

        // Capítulos → Mapa de níveis do capítulo 1
        composeRule.onNodeWithText("Reino das Frutas").performClick()
        composeRule.onNodeWithText("Progresso: 0/10").assertIsDisplayed()

        // Mapa → Fase (nível 1)
        composeRule.onNodeWithText("1").performClick()
        composeRule.onNodeWithText("NÍVEL 1").assertIsDisplayed()
        composeRule.onNodeWithText("DICA").assertIsDisplayed()
    }
}