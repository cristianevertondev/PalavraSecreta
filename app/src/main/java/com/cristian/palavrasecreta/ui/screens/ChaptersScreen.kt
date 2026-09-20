package com.cristian.palavrasecreta.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristian.palavrasecreta.data.model.Chapter
import com.cristian.palavrasecreta.ui.theme.CompletedColor
import com.cristian.palavrasecreta.ui.theme.LockedColor

@Composable
fun ChaptersScreen(
    chapters: List<Chapter>,
    completedLevels: Set<Int>,
    isChapterUnlocked: (Chapter) -> Boolean,
    onBack: () -> Unit,
    onChapterClick: (Chapter) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
            }
            Text(
                text = "Capítulos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 20.dp, end = 20.dp, bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(chapters, key = { it.id }) { chapter ->
                ChapterCard(
                    chapter = chapter,
                    completedLevels = completedLevels,
                    unlocked = isChapterUnlocked(chapter),
                    onClick = {
                        if (isChapterUnlocked(chapter) && chapter.isPlayable) onChapterClick(chapter)
                    }
                )
            }
        }
    }
}

@Composable
private fun ChapterCard(
    chapter: Chapter,
    completedLevels: Set<Int>,
    unlocked: Boolean,
    onClick: () -> Unit
) {
    val theme = chapter.visualTheme
    val done = chapter.levels.count { it.id in completedLevels }
    val total = chapter.levels.size
    val progressText = when {
        !chapter.isPlayable -> "Em breve"
        done == total -> "Concluído"
        else -> "Progresso: $done/$total"
    }

    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = when {
            !chapter.isPlayable -> LockedColor.copy(alpha = 0.45f)
            !unlocked -> LockedColor.copy(alpha = 0.45f)
            else -> theme.primary
        },
        contentColor = androidx.compose.ui.graphics.Color.White
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = chapter.icon, fontSize = 34.sp)
                Spacer(Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CAPÍTULO ${chapter.id}",
                        style = MaterialTheme.typography.labelLarge,
                        color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.85f),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = chapter.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = when {
                        !chapter.isPlayable || !unlocked -> Icons.Default.Lock
                        done == total -> Icons.Default.CheckCircle
                        else -> Icons.Default.PlayArrow
                    },
                    contentDescription = null,
                    tint = when {
                        done == total -> CompletedColor
                        else -> androidx.compose.ui.graphics.Color.White
                    }
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = if (chapter.isPlayable) "$total níveis" else "Novos níveis em breve",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = progressText,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}