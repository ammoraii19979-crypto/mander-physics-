package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.data.content.FlashcardBank
import com.example.data.content.QuestionBank
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.SectionProgressEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.Section
import com.example.ui.navigation.SectionStage
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.ScheduleInfo

@Composable
fun HomeScreen(
    todaySection: Section?,
    lastStoppedSection: Section?,
    sectionProgress: Map<String, SectionProgressEntity>,
    scheduleInfo: ScheduleInfo,
    userProgress: UserProgressEntity?,
    conceptMasteries: Map<String, ConceptMasteryEntity>,
    questionAttempts: List<QuestionAttemptEntity>,
    onStartToday: () -> Unit,
    onResumeStopped: () -> Unit,
    onOpenSection: (Int, String, SectionStage) -> Unit,
    onToggleRead: (String) -> Unit,
    onToggleQuestions: (String) -> Unit,
    onToggleEnforce: (String) -> Unit,
    onOpenSearch: () -> Unit,
    onOpenBookmarks: () -> Unit,
    onOpenSetup: () -> Unit,
    onViewProgress: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeToday = todaySection ?: CurriculumData.allSections.first()
    val activeProgress = sectionProgress[activeToday.id]
    val (startPage, endPage) = CurriculumData.getSectionPageRange(activeToday)
    val pageCount = CurriculumData.getSectionPageCount(activeToday)

    val todayQuestions = QuestionBank.getQuestionsBySection(activeToday.id)
    val todayCards = FlashcardBank.getFlashcardsBySection(activeToday.id)

    val isReadDone = activeProgress?.isReadCompleted ?: false
    val isQDone = activeProgress?.isQuestionsCompleted ?: false
    val isEnforceDone = activeProgress?.isEnforceCompleted ?: false
    val isTodayAllDone = isReadDone && isQDone && isEnforceDone

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_scroll"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Brand Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Farr's Physics",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MedicalBlue
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MedicalTeal.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "FRCR GPS",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = MedicalTeal,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = "Physics for Medical Imaging (2nd Ed)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    IconButton(onClick = onOpenSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onSurface)
                    }
                    IconButton(onClick = onOpenBookmarks) {
                        Icon(Icons.Default.BookmarkBorder, contentDescription = "Bookmarks", tint = MaterialTheme.colorScheme.onSurface)
                    }
                    IconButton(onClick = onOpenSetup) {
                        Icon(Icons.Default.Settings, contentDescription = "Plan Settings", tint = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }

        // 1. TODAY'S STUDY CARD
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("today_study_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Header Badge & Section Location
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MedicalBlue
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    Icons.Default.EventAvailable,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "TODAY'S STUDY",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        if (isTodayAllDone) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = SuccessGreen.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "Completed ✅",
                                    color = SuccessGreen,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            Text(
                                text = "~45 min study",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Section Info
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Chapter ${activeToday.chapterId} • Section ${activeToday.number}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MedicalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = activeToday.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Farr Printed Pages $startPage–$endPage ($pageCount pages)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Interactive Checklist: READ -> QUESTIONS -> ENFORCE
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // 1. Read
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onToggleRead(activeToday.id) }
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isReadDone,
                                onCheckedChange = { onToggleRead(activeToday.id) },
                                colors = CheckboxDefaults.colors(checkedColor = SuccessGreen)
                            )
                            Spacer(Modifier.width(6.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "1. Read — Farr's Notes & Summary",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Pages $startPage–$endPage",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(
                                onClick = { onOpenSection(activeToday.chapterId, activeToday.id, SectionStage.READ) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Read", modifier = Modifier.size(16.dp))
                            }
                        }

                        // 2. Questions
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onToggleQuestions(activeToday.id) }
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isQDone,
                                onCheckedChange = { onToggleQuestions(activeToday.id) },
                                colors = CheckboxDefaults.colors(checkedColor = SuccessGreen)
                            )
                            Spacer(Modifier.width(6.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "2. Questions — FRCR Physics Bank",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "${todayQuestions.size} questions mapped",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(
                                onClick = { onOpenSection(activeToday.chapterId, activeToday.id, SectionStage.QUESTIONS) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Questions", modifier = Modifier.size(16.dp))
                            }
                        }

                        // 3. Enforce / Anki
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onToggleEnforce(activeToday.id) }
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isEnforceDone,
                                onCheckedChange = { onToggleEnforce(activeToday.id) },
                                colors = CheckboxDefaults.colors(checkedColor = SuccessGreen)
                            )
                            Spacer(Modifier.width(6.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "3. Enforce — Flashcards / Anki",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "${todayCards.size} cards mapped",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(
                                onClick = { onOpenSection(activeToday.chapterId, activeToday.id, SectionStage.ENFORCE) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Enforce", modifier = Modifier.size(16.dp))
                            }
                        }
                    }

                    // Big Action Button
                    Button(
                        onClick = onStartToday,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("start_today_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = if (isTodayAllDone) "Review Today's Section" else "Start Today's Study",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }

        // 2. CONTINUE WHERE YOU STOPPED
        val stopped = lastStoppedSection
        if (stopped != null) {
            val (stopStart, stopEnd) = CurriculumData.getSectionPageRange(stopped)
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("continue_stopped_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "CONTINUE WHERE YOU STOPPED",
                                style = MaterialTheme.typography.labelSmall,
                                color = MedicalBlue,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = "Chapter ${stopped.chapterId}: ${stopped.title}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Section ${stopped.number} • Pages $stopStart–$stopEnd",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        FilledTonalButton(
                            onClick = onResumeStopped,
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text("Resume", fontSize = 13.sp)
                            Spacer(Modifier.width(4.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }
        }

        // 3. OVERALL PROGRESS & SCHEDULE CARD
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onViewProgress() }
                    .testTag("overall_progress_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "OVERALL BOOK PROGRESS",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${scheduleInfo.overallProgressPercent}% Complete",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MedicalBlue
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MedicalTeal.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "${scheduleInfo.completedPages} / ${scheduleInfo.totalPages} pages",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MedicalTeal,
                                fontWeight = FontWeight.Bold
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

                    // Submetrics: Chapters & Sections
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Chapters: ${scheduleInfo.completedChaptersCount} / ${scheduleInfo.totalChaptersCount}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Sections: ${scheduleInfo.completedSectionsCount} / ${scheduleInfo.totalSectionsCount}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Smart Schedule Health Bar
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = when {
                            scheduleInfo.daysAheadOrBehind > 0 -> SuccessGreen.copy(alpha = 0.1f)
                            scheduleInfo.daysAheadOrBehind < 0 -> MedicalAmber.copy(alpha = 0.12f)
                            else -> MedicalBlue.copy(alpha = 0.08f)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = scheduleInfo.scheduleStatusText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = when {
                                        scheduleInfo.daysAheadOrBehind > 0 -> SuccessGreen
                                        scheduleInfo.daysAheadOrBehind < 0 -> Color(0xFFC05621)
                                        else -> MedicalBlue
                                    }
                                )
                                Text(
                                    text = "Finish by ${scheduleInfo.estimatedCompletionDateFormatted} • ${scheduleInfo.currentPaceNeeded} pp/day",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        // 4. TRAINEE MASTERY CIRCULAR CARD
        item {
            TraineeMasteryCard(
                conceptMasteries = conceptMasteries,
                questionAttempts = questionAttempts,
                onViewProgress = onViewProgress
            )
        }
    }
}
