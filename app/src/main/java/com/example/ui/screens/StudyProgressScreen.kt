package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumData
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.SectionProgressEntity
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.ScheduleInfo

@Composable
fun StudyProgressScreen(
    scheduleInfo: ScheduleInfo,
    sectionProgress: Map<String, SectionProgressEntity>,
    conceptMasteries: Map<String, ConceptMasteryEntity>,
    questionAttempts: List<QuestionAttemptEntity>,
    onHandleMissedDays: (keepOriginalDate: Boolean) -> Unit,
    onOpenSetup: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chapters = CurriculumData.chapters

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("progress_screen_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MedicalBlue.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = "STUDY METRICS & GPS",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MedicalBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Study Progress",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Farr's Physics for Medical Imaging (2nd Edition)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Circular Trainee Mastery Card
        item {
            TraineeMasteryCard(
                conceptMasteries = conceptMasteries,
                questionAttempts = questionAttempts,
                onViewProgress = {}
            )
        }

        // Main Transparent Progress Summary Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "TRANSPARENT STUDY PROGRESS",
                        style = MaterialTheme.typography.labelSmall,
                        color = MedicalBlue,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                text = "${scheduleInfo.overallProgressPercent}%",
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = MedicalBlue
                            )
                            Text(
                                text = "of Farr's textbook completed",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${scheduleInfo.completedPages} / ${scheduleInfo.totalPages} pages",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${scheduleInfo.remainingPages} pages remaining",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    LinearProgressIndicator(
                        progress = { scheduleInfo.overallProgressPercent / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(CircleShape),
                        color = MedicalBlue,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                    // 4 Pillar Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Reading
                        Column {
                            Text("READING", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("${scheduleInfo.completedPages} pp", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("of ${scheduleInfo.totalPages} pages", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }

                        // Chapters
                        Column {
                            Text("CHAPTERS", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("${scheduleInfo.completedChaptersCount} / 10", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("fully completed", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }

                        // Questions
                        Column {
                            Text("QUESTIONS", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("${scheduleInfo.completedQuestionsCount} done", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("${scheduleInfo.questionAccuracyPercent}% accuracy", style = MaterialTheme.typography.labelSmall, color = SuccessGreen)
                        }

                        // Anki
                        Column {
                            Text("ANKI", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("${scheduleInfo.completedAnkiCount} cards", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("reviewed", style = MaterialTheme.typography.labelSmall, color = MedicalTeal)
                        }
                    }
                }
            }
        }

        // Smart Schedule & Missed Days Redistribution Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        scheduleInfo.daysAheadOrBehind > 0 -> SuccessGreen.copy(alpha = 0.08f)
                        scheduleInfo.daysAheadOrBehind < 0 -> MedicalAmber.copy(alpha = 0.1f)
                        else -> MedicalBlue.copy(alpha = 0.06f)
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "SMART SCHEDULE & REBALANCING",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MedicalBlue
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Text(
                                text = scheduleInfo.scheduleStatusText,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = when {
                                    scheduleInfo.daysAheadOrBehind > 0 -> SuccessGreen
                                    scheduleInfo.daysAheadOrBehind < 0 -> Color(0xFFC05621)
                                    else -> MedicalBlue
                                }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Target Daily Pace", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("${scheduleInfo.targetPagesPerDay} pp/day", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Current Pace Needed", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("${scheduleInfo.currentPaceNeeded} pp/day", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MedicalBlue)
                        }
                        Column {
                            Text("Est. Completion", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(scheduleInfo.estimatedCompletionDateFormatted, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Missed days dynamic redistribute options
                    if (scheduleInfo.daysAheadOrBehind < 0) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "You are behind schedule. How would you like to redistribute?",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { onHandleMissedDays(true) },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue)
                                ) {
                                    Text("Keep Date (${scheduleInfo.currentPaceNeeded} pp/d)", fontSize = 11.sp)
                                }
                                OutlinedButton(
                                    onClick = { onHandleMissedDays(false) },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Extend Completion Date", fontSize = 11.sp)
                                }
                            }
                        }
                    }

                    OutlinedButton(
                        onClick = onOpenSetup,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Schedule, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Edit Study Schedule Settings")
                    }
                }
            }
        }

        // Chapter by Chapter Completion List
        item {
            Text(
                text = "CHAPTER BREAKDOWN",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
        }

        items(chapters, key = { it.id }) { chapter ->
            val sections = chapter.sections
            val readCount = sections.count { sectionProgress[it.id]?.isReadCompleted == true }
            val percent = if (sections.isNotEmpty()) (readCount * 100) / sections.size else 0

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Ch ${chapter.id}",
                                style = MaterialTheme.typography.labelMedium,
                                color = MedicalBlue,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = chapter.title,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "$readCount/${sections.size} sections ($percent%)",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    LinearProgressIndicator(
                        progress = { percent / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = if (percent == 100) SuccessGreen else MedicalBlue,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round
                    )
                }
            }
        }
    }
}
