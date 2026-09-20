package com.cristian.palavrasecreta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristian.palavrasecreta.data.model.ChapterVisualTheme

/**
 * Fundo decorativo de um capítulo: gradiente suave + emojis flutuantes discretos.
 * Conteúdo principal permanece legível.
 */
@Composable
fun ChapterBackground(theme: ChapterVisualTheme, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(theme.gradientTop, theme.gradientBottom)))
    ) {
        FloatingDecor(theme)
        content()
    }
}

@Composable
private fun BoxScope.FloatingDecor(theme: ChapterVisualTheme) {
    val offset = 40.dp
    theme.decorEmojis.forEachIndexed { index, emoji ->
        val horizontal = when (index % 3) {
            0 -> Modifier.align(Alignment.TopStart)
            1 -> Modifier.align(Alignment.TopEnd)
            else -> Modifier.align(Alignment.TopCenter)
        }
        Box(
            modifier = horizontal
                .padding(top = (18 + index * 26).dp)
                .offset(x = if (index % 2 == 0) offset else (-offset))
                .size(42.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 30.sp, color = theme.secondary.copy(alpha = 0.55f))
        }
    }
}

/**
 * Cartão arredondado usado como placa/tabuleiro nas telas do jogo.
 */
@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White.copy(alpha = 0.95f),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        color = backgroundColor,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shadowElevation = 6.dp
    ) {
        content()
    }
}

/**
 * Selo de dificuldade exibido no tabuleiro.
 */
@Composable
fun DifficultyChip(label: String, color: Color, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = color.copy(alpha = 0.15f)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            color = color,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Cabeçalho compacto de capítulo (título + tema + progresso) usado nas telas.
 */
@Composable
fun ChapterHeader(
    icon: String,
    title: String,
    themeName: String,
    progressText: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = icon, fontSize = 28.sp)
            Text(
                text = "  ",
                fontSize = 1.sp
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "Tema: $themeName",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Text(
            text = progressText,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}