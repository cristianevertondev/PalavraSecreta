package com.cristian.palavrasecreta.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristian.palavrasecreta.data.model.Chapter
import com.cristian.palavrasecreta.data.model.Difficulty
import com.cristian.palavrasecreta.data.model.Level
import com.cristian.palavrasecreta.ui.components.ChapterBackground
import com.cristian.palavrasecreta.ui.components.DifficultyChip
import com.cristian.palavrasecreta.ui.components.GameCard
import com.cristian.palavrasecreta.data.model.ChapterVisualTheme
import com.cristian.palavrasecreta.viewmodel.FeedbackType
import com.cristian.palavrasecreta.viewmodel.GameUiState

@Composable
fun GameScreen(
    chapter: Chapter,
    theme: ChapterVisualTheme,
    state: GameUiState,
    onBack: () -> Unit,
    onInputChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNextLevel: () -> Unit,
    onRetry: () -> Unit
) {
    val level = state.level ?: return
    val shakeOffset = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(state.attemptsUsed) {
        val fb = state.feedback?.first
        if (fb == FeedbackType.WRONG || fb == FeedbackType.OUT_OF_ATTEMPTS) {
            shakeOffset.snapTo(0f)
            repeat(4) { i ->
                shakeOffset.animateTo(if (i % 2 == 0) -12f else 12f, tween(70))
            }
            shakeOffset.snapTo(0f)
        }
    }

    LaunchedEffect(state.complete) {
        if (state.complete) {
            scale.snapTo(0.85f)
            scale.animateTo(1.1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
            scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
        }
    }

    ChapterBackground(theme = theme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(onBack = onBack, chapterId = chapter.id, levelId = level.id, theme = theme)

            GameCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .graphicsLayer {
                            translationX = shakeOffset.value
                            scaleX = scale.value
                            scaleY = scale.value
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${chapter.icon}  ${chapter.title}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = theme.primary
                        )
                        Spacer(Modifier.weight(1f))
                        DifficultyChip(
                            label = level.difficulty.label,
                            color = difficultyColor(level.difficulty)
                        )
                    }

                    Spacer(Modifier.height(14.dp))
                    Text(
                        text = "NÍVEL ${level.id}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Black,
                        color = theme.accent
                    )

                    Spacer(Modifier.height(16.dp))
                    WordBoxes(
                        word = level.word,
                        revealed = state.complete
                    )

                    Spacer(Modifier.height(18.dp))
                    AttemptsIndicator(
                        attemptsLeft = (level.maxAttempts - state.attemptsUsed).coerceAtLeast(0),
                        maxAttempts = level.maxAttempts
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 14.dp))

                    Text(
                        text = "DICA",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = theme.primary
                    )
                    Spacer(Modifier.height(6.dp))
                    // Mostra as dicas reveladas até o momento.
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        level.hints.take(state.revealedHints).forEachIndexed { idx, hint ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(text = "🔍 ", fontSize = 14.sp)
                                Text(
                                    text = hint,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                    if (state.revealedHints < level.hints.size) {
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Erre para revelar a próxima dica.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            // Área de resposta (escondida quando completo ou sem tentativas).
            AnimatedVisibility(
                visible = !state.complete && !state.outOfAttempts
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = state.input,
                        onValueChange = onInputChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Digite sua resposta...") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Ascii,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { onSubmit() }),
                        shape = RoundedCornerShape(16.dp),
                        textStyle = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = onSubmit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = theme.primary
                        )
                    ) {
                        Text("TENTAR", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Feedback de erro (mensagem temporária).
            AnimatedVisibility(visible = state.feedback?.first == FeedbackType.WRONG) {
                FeedbackBanner(text = state.feedback?.second.orEmpty(), color = theme.accent)
            }

            // Estado: tentativas esgotadas → revela a resposta e permite tentar de novo.
            AnimatedVisibility(visible = state.outOfAttempts) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FeedbackBanner(text = state.feedback?.second.orEmpty(), color = theme.accent)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = onRetry,
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("TENTAR NOVAMENTE", fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Estado: acertou → overlay de sucesso com próximo nível.
            AnimatedVisibility(visible = state.complete) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = CompletedGreen.copy(alpha = 0.15f)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "🎉", fontSize = 44.sp)
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = "CORRETO!",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Black,
                                color = CompletedGreen
                            )
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    Button(
                        onClick = onNextLevel,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CompletedGreen
                        )
                    ) {
                        Text("PRÓXIMO NÍVEL  ▶", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    onBack: () -> Unit,
    chapterId: Int,
    levelId: Int,
    theme: ChapterVisualTheme
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(shape = CircleShape, color = Color.White.copy(alpha = 0.85f)) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar"
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Surface(
            shape = RoundedCornerShape(50),
            color = theme.secondary.copy(alpha = 0.9f)
        ) {
            Text(
                text = "Cap. $chapterId · Nível $levelId",
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun WordBoxes(word: String, revealed: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        word.forEach { ch ->
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (revealed) CompletedGreen else Color.White,
                border = androidx.compose.foundation.BorderStroke(
                    2.dp,
                    if (revealed) CompletedGreen else MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
            ) {
                Text(
                    text = if (revealed) ch.toString() else "",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Black,
                    color = if (revealed) Color.White else Color.Transparent,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun AttemptsIndicator(attemptsLeft: Int, maxAttempts: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Tentativas:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(Modifier.width(8.dp))
        repeat(maxAttempts) { i ->
            Text(
                text = "●",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 2.dp),
                color = if (i < attemptsLeft) CompletedGreen else Color.Gray
            )
        }
    }
}

@Composable
private fun FeedbackBanner(text: String, color: Color) {
    Spacer(Modifier.height(12.dp))
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = color.copy(alpha = 0.14f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(14.dp),
            color = color,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

private fun difficultyColor(difficulty: Difficulty): Color = when (difficulty) {
    Difficulty.EASY -> CompletedGreen
    Difficulty.MEDIUM -> Color(0xFFF9A825)
    Difficulty.HARD -> Color(0xFFE53935)
}

private val CompletedGreen = Color(0xFF4CAF50)