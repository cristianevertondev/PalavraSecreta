package com.cristian.palavrasecreta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristian.palavrasecreta.data.model.Chapter
import com.cristian.palavrasecreta.data.model.Level
import com.cristian.palavrasecreta.ui.components.ChapterBackground
import com.cristian.palavrasecreta.ui.components.ChapterHeader
import com.cristian.palavrasecreta.ui.theme.CompletedColor
import com.cristian.palavrasecreta.ui.theme.LockedColor

@Composable
fun LevelMapScreen(
    chapter: Chapter,
    completedLevels: Set<Int>,
    isLevelUnlocked: (Int) -> Boolean,
    isLevelCompleted: (Int) -> Boolean,
    onBack: () -> Unit,
    onLevelClick: (Level) -> Unit
) {
    val theme = chapter.visualTheme
    val done = chapter.levels.count { it.id in completedLevels }

    ChapterBackground(theme = theme) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = CircleShape, color = Color.White.copy(alpha = 0.85f)) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            }

            ChapterHeader(
                icon = chapter.icon,
                title = chapter.title,
                themeName = chapter.themeName,
                progressText = "Progresso: $done/${chapter.levels.size}"
            )
            Spacer(Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 32.dp, vertical = 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(chapter.levels.sortedBy { it.id }) { index, level ->
                    LevelNode(
                        level = level,
                        offset = if (index % 2 == 0) (-26).dp else 26.dp,
                        unlocked = isLevelUnlocked(level.id),
                        completed = isLevelCompleted(level.id),
                        isLast = index == chapter.levels.lastIndex,
                        onClick = { if (isLevelUnlocked(level.id)) onLevelClick(level) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelNode(
    level: Level,
    offset: androidx.compose.ui.unit.Dp,
    unlocked: Boolean,
    completed: Boolean,
    isLast: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offset),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Surface(
                onClick = onClick,
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = when {
                    completed -> CompletedColor
                    unlocked -> Color.White.copy(alpha = 0.92f)
                    else -> LockedColor.copy(alpha = 0.35f)
                },
                shadowElevation = if (unlocked) 6.dp else 0.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    when {
                        completed -> Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Nível ${level.id} concluído",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                        !unlocked -> Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Nível ${level.id} bloqueado",
                            tint = Color.Gray
                        )
                        else -> Text(
                            text = "${level.id}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
        if (!isLast) {
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(24.dp)
                    .background(
                        if (completed) CompletedColor else LockedColor.copy(alpha = 0.3f),
                        RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}