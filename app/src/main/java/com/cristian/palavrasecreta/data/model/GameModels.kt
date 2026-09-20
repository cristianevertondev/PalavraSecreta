package com.cristian.palavrasecreta.data.model

import androidx.compose.ui.graphics.Color

/**
 * Nível de dificuldade de uma fase. Cresce conforme o jogador avança.
 */
enum class Difficulty(val label: String) {
    EASY("Fácil"),
    MEDIUM("Médio"),
    HARD("Difícil")
}

/**
 * Uma fase jogável: a palavra secreta, suas dicas e os limites de tentativas.
 */
data class Level(
    val id: Int,
    val chapterId: Int,
    val word: String,
    val hints: List<String>,
    val difficulty: Difficulty,
    val maxAttempts: Int
)

/**
 * Identidade visual de um capítulo: paleta, gradiente e decorações.
 * A UI permanece a mesma para todos os capítulos; apenas estes valores mudam.
 * Fica no modelo para que o capítulo carregue consigo tudo que a UI precisa.
 */
data class ChapterVisualTheme(
    val themeName: String,
    val icon: String,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val gradientTop: Color,
    val gradientBottom: Color,
    val decorEmojis: List<String>
)

/**
 * Um capítulo do jogo, agrupando níveis sob um tema e uma identidade visual.
 * `themeName` e `icon` são derivados do tema (fonte única de verdade).
 */
data class Chapter(
    val id: Int,
    val title: String,
    val visualTheme: ChapterVisualTheme,
    val levels: List<Level>
) {
    val themeName: String get() = visualTheme.themeName
    val icon: String get() = visualTheme.icon

    /** Capítulo ainda sem níveis (placeholder aguardando conteúdo). */
    val isPlayable: Boolean get() = levels.isNotEmpty()
}