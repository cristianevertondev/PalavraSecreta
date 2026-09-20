package com.cristian.palavrasecreta.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cristian.palavrasecreta.data.content.GameContent
import com.cristian.palavrasecreta.ui.screens.ChaptersScreen
import com.cristian.palavrasecreta.ui.screens.GameScreen
import com.cristian.palavrasecreta.ui.screens.HomeScreen
import com.cristian.palavrasecreta.ui.screens.LevelMapScreen
import com.cristian.palavrasecreta.viewmodel.GameViewModel
import com.cristian.palavrasecreta.viewmodel.Screen

@Composable
fun PalavraSecretaApp(viewModel: GameViewModel = viewModel()) {
    val navStack by viewModel.navStack.collectAsState()
    val completedLevels by viewModel.completedLevels.collectAsState()
    val gameState by viewModel.gameState.collectAsState()
    val audioSettings by viewModel.audioSettings.collectAsState()

    val current = navStack.last()

    BackHandler(enabled = navStack.size > 1) {
        viewModel.goBack()
    }

    when (val screen = current) {
        is Screen.Home -> HomeScreen(
            onPlay = {
                val chapter = viewModel.currentChapterForContinue()
                if (chapter != null) viewModel.openLevelMap(chapter.id)
            },
            onChapters = { viewModel.openChapters() },
            musicOn = audioSettings.musicOn,
            sfxOn = audioSettings.sfxOn,
            onToggleMusic = viewModel::setMusicOn,
            onToggleSfx = viewModel::setSfxOn
        )

        is Screen.Chapters -> ChaptersScreen(
            chapters = viewModel.chapters,
            completedLevels = completedLevels,
            isChapterUnlocked = viewModel::isChapterUnlocked,
            onBack = { viewModel.goBack() },
            onChapterClick = { viewModel.openLevelMap(it.id) }
        )

        is Screen.LevelMap -> {
            val chapter = GameContent.chapterById(screen.chapterId)
            if (chapter != null) {
                LevelMapScreen(
                    chapter = chapter,
                    completedLevels = completedLevels,
                    isLevelUnlocked = viewModel::isLevelUnlocked,
                    isLevelCompleted = viewModel::isLevelCompleted,
                    onBack = { viewModel.goBack() },
                    onLevelClick = { viewModel.startLevel(chapter.id, it.id) }
                )
            }
        }

        is Screen.Game -> {
            val chapter = GameContent.chapterById(screen.chapterId)
            if (chapter != null) {
                GameScreen(
                    chapter = chapter,
                    theme = chapter.visualTheme,
                    state = gameState,
                    onBack = { viewModel.goBack() },
                    onInputChange = viewModel::onInputChange,
                    onSubmit = viewModel::onSubmit,
                    onNextLevel = viewModel::onNextLevel,
                    onRetry = viewModel::retryLevel
                )
            }
        }
    }
}