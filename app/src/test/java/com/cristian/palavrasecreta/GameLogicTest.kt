package com.cristian.palavrasecreta

import com.cristian.palavrasecreta.data.GameLogic
import com.cristian.palavrasecreta.data.content.GameContent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameLogicTest {

    @Test
    fun normalize_removesAccentsAndTrims() {
        assertEquals("MACA", GameLogic.normalizeAnswer("maçã"))
        assertEquals("MACA", GameLogic.normalizeAnswer("  Maçã  "))
        assertEquals("BANANA", GameLogic.normalizeAnswer("banana"))
        assertEquals("LEAO", GameLogic.normalizeAnswer("Leão"))
    }

    @Test
    fun firstLevelUnlockedWithNoProgress() {
        val levels = GameContent.allLevels
        assertTrue(GameLogic.isLevelUnlocked(levels, emptySet(), levels.first().id))
    }

    @Test
    fun laterLevelLockedUntilPreviousCompleted() {
        val levels = GameContent.allLevels
        assertFalse(GameLogic.isLevelUnlocked(levels, emptySet(), levels[1].id))
        assertTrue(GameLogic.isLevelUnlocked(levels, setOf(levels[0].id), levels[1].id))
    }

    @Test
    fun chapterTwoUnlocksAfterCompletingChapterOne() {
        val levels = GameContent.allLevels
        // nível 11 (1º do capítulo 2) só desbloqueia após concluir o nível 10.
        val level11 = GameContent.levelById(11)!!
        assertFalse(GameLogic.isLevelUnlocked(levels, emptySet(), level11.id))
        assertTrue(GameLogic.isLevelUnlocked(levels, setOf(10), level11.id))
    }

    @Test
    fun nonexistentLevelIsLocked() {
        assertFalse(GameLogic.isLevelUnlocked(GameContent.allLevels, emptySet(), 999))
    }

    @Test
    fun firstChapterAlwaysUnlocked() {
        assertTrue(GameLogic.isChapterUnlocked(GameContent.chapters, emptySet(), 1))
    }

    @Test
    fun chapterTwoLockedUntilChapterOneComplete() {
        val ch2 = GameContent.chapters[1]
        // Capítulo 1 tem os níveis 1..10; capítulo 2 só desbloqueia quando todos concluídos.
        assertFalse(GameLogic.isChapterUnlocked(GameContent.chapters, emptySet(), ch2.id))
        assertFalse(GameLogic.isChapterUnlocked(GameContent.chapters, setOf(1, 2, 3, 4, 5, 6, 7, 8, 9), ch2.id))
        assertTrue(GameLogic.isChapterUnlocked(GameContent.chapters, (1..10).toSet(), ch2.id))
    }

    @Test
    fun chapterThreeUnlocksWhenChapterTwoComplete() {
        val ch3 = GameContent.chapters[2]
        assertFalse(GameLogic.isChapterUnlocked(GameContent.chapters, (1..10).toSet(), ch3.id))
        assertTrue(GameLogic.isChapterUnlocked(GameContent.chapters, (1..20).toSet(), ch3.id))
    }

    @Test
    fun lastChapterRecognizedByCollectionPosition() {
        assertEquals(30, GameContent.chapters.last().id)
    }

    @Test
    fun nonexistentChapterAfterLastIsNotUnlocked() {
        // Não há tentativa de desbloquear um capítulo inexistente depois do 30.
        assertFalse(GameLogic.isChapterUnlocked(GameContent.chapters, (1..20).toSet(), 31))
        assertFalse(GameLogic.isChapterUnlocked(GameContent.chapters, emptySet(), 999))
    }
}

class GameContentTest {

    @Test
    fun hasThirtyChapters() {
        assertEquals(30, GameContent.chapters.size)
    }

    @Test
    fun chapterIdsAreUniqueAndSequential() {
        assertEquals((1..30).toList(), GameContent.chapters.map { it.id })
    }

    @Test
    fun everyChapterHasTitleThemeAndIcon() {
        GameContent.chapters.forEach { chapter ->
            assertTrue("Título vazio no capítulo ${chapter.id}", chapter.title.isNotBlank())
            assertTrue("Tema vazio no capítulo ${chapter.id}", chapter.themeName.isNotBlank())
            assertTrue("Ícone vazio no capítulo ${chapter.id}", chapter.icon.isNotBlank())
        }
    }

    @Test
    fun everyChapterHasValidVisualTheme() {
        GameContent.chapters.forEach { chapter ->
            val t = chapter.visualTheme
            assertTrue("Gradiente inválido no capítulo ${chapter.id}", t.gradientTop != t.gradientBottom)
            assertTrue("Decorações vazias no capítulo ${chapter.id}", t.decorEmojis.isNotEmpty())
            // Primary nunca é "branco puro" (indicaria cor não definida).
            assertFalse("Primary inválido no capítulo ${chapter.id}", t.primary == androidx.compose.ui.graphics.Color.White)
        }
    }

    @Test
    fun everyChapterHasTenLevels() {
        GameContent.chapters.forEach { chapter ->
            assertEquals(
                "Capítulo ${chapter.id} deveria ter 10 níveis",
                10,
                chapter.levels.size
            )
        }
    }

    @Test
    fun allLevelsTotalThreeHundred() {
        assertEquals(300, GameContent.allLevels.size)
    }

    @Test
    fun existingLevelIdsArePreserved() {
        assertEquals((1..300).toList(), GameContent.allLevels.map { it.id })
    }

    @Test
    fun globalLevelIdsHaveNoDuplicates() {
        val ids = GameContent.allLevels.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun globalLevelOrderFollowsChapters() {
        // A ordem global vem da ordem dos capítulos na coleção.
        val fromChapters = GameContent.chapters.flatMap { it.levels }.map { it.id }
        assertEquals(fromChapters, GameContent.allLevels.map { it.id })
    }

    @Test
    fun allChaptersArePlayable() {
        GameContent.chapters.forEach { chapter ->
            assertTrue(
                "Capítulo ${chapter.id} deve ser jogável",
                chapter.isPlayable
            )
        }
    }

    @Test
    fun everyPlayableLevelHasWordHintsAndAttempts() {
        GameContent.allLevels.forEach { level ->
            assertTrue("Palavra vazia no nível ${level.id}", level.word.isNotBlank())
            assertTrue("Sem dicas no nível ${level.id}", level.hints.isNotEmpty())
            level.hints.forEach { assertTrue("Dica vazia no nível ${level.id}", it.isNotBlank()) }
            assertTrue("Tentativas inválidas no nível ${level.id}", level.maxAttempts > 0)
        }
    }

    @Test
    fun wordsAreUniqueWithinEachPlayableChapter() {
        GameContent.chapters.filter { it.isPlayable }.forEach { chapter ->
            val words = chapter.levels.map { GameLogic.normalizeAnswer(it.word) }
            assertEquals(
                "Palavra repetida no capítulo ${chapter.id}",
                words.size,
                words.toSet().size
            )
        }
    }
}