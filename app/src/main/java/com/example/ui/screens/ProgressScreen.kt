package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumData
import com.example.data.local.BookmarkEntity
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.ui.navigation.PaceStage
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.PaceChallengeColor
import com.example.ui.theme.PaceEnforceColor
import com.example.ui.theme.SuccessGreen

@Composable
fun ProgressScreen(
    conceptMasteries: Map<String, ConceptMasteryEntity>,
    questionAttempts: List<QuestionAttemptEntity>,
    bookmarks: List<BookmarkEntity>,
    onSelectConcept: (String, PaceStage) -> Unit,
    modifier: Modifier = Modifier
) {
    val allConcepts = CurriculumData.getAllConcepts()
    val totalConcepts = allConcepts.size

    val primeDone = conceptMasteries.values.count { it.primeCompleted }
    val acquireDone = conceptMasteries.values.count { it.acquireCompleted }
    val totalAttempts = questionAttempts.size
    val correctAttempts = questionAttempts.count { it.isCorrect }
    val questionAccuracy = if (totalAttempts > 0) (correctAttempts * 100) / totalAttempts else 0

    // Readiness score out of 100
    val readinessScore = (
        (primeDone.toFloat() / totalConcepts.toFloat() * 30f) +
        (acquireDone.toFloat() / totalConcepts.toFloat() * 30f) +
        (questionAccuracy.toFloat() * 0.4f)
    ).toInt().coerceIn(0, 100)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("progress_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Overall Readiness Score Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "FRCR PHYSICS READINESS SCORE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "$readinessScore%",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = when {
                            readinessScore >= 80 -> "Excellent preparation! Ready for FRCR Part 1 physics."
                            readinessScore >= 50 -> "Good momentum. Continue challenging with Q-Bank."
                            readinessScore >= 20 -> "Foundations forming. Keep completing PACE cycles."
                            else -> "Begin your journey across the 10 Farr's Physics chapters."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    LinearProgressIndicator(
                        progress = { readinessScore / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        // Stats Triad Grid
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatPill(
                    title = "Prime Done",
                    value = "$primeDone / $totalConcepts",
                    color = MedicalTeal,
                    modifier = Modifier.weight(1f)
                )
                StatPill(
                    title = "Q-Bank Accuracy",
                    value = "$questionAccuracy%",
                    color = PaceChallengeColor,
                    modifier = Modifier.weight(1f)
                )
                StatPill(
                    title = "Attempts",
                    value = "$totalAttempts MCQs",
                    color = MedicalBlue,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Chapter Mastery Breakdown Header
        item {
            Text(
                text = "Curriculum Chapter Breakdown",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // 10 Chapter Progress Bars
        items(CurriculumData.chapters) { chapter ->
            val concepts = chapter.sections.flatMap { it.concepts }
            val masteredCount = concepts.count { c ->
                val m = conceptMasteries[c.id]
                m != null && m.primeCompleted && m.acquireCompleted
            }
            val chPercent = if (concepts.isNotEmpty()) masteredCount.toFloat() / concepts.size.toFloat() else 0f

            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ch ${chapter.number}: ${chapter.title}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "$masteredCount / ${concepts.size}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { chPercent },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = getChapterAccentColor(chapter.number),
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        // Bookmarked Topics for Rapid Revision
        if (bookmarks.isNotEmpty()) {
            item {
                Text(
                    text = "Saved Bookmarks for Rapid Revision (${bookmarks.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            items(bookmarks) { b ->
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (b.type == "CONCEPT") {
                                onSelectConcept(b.targetId, PaceStage.PRIME)
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Icon(imageVector = Icons.Default.Bookmark, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(b.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                                Text(b.subtitle, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StatPill(
    title: String,
    value: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.labelSmall, color = color, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        }
    }
}
