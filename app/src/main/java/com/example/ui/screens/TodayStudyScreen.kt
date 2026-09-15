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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
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
import com.example.data.local.SectionProgressEntity
import com.example.data.model.Section
import com.example.ui.navigation.SectionStage
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.SuccessGreen

@Composable
fun TodayStudyScreen(
    todaySection: Section?,
    sectionProgress: Map<String, SectionProgressEntity>,
    onOpenSection: (Int, String, SectionStage) -> Unit,
    onToggleRead: (String) -> Unit,
    onToggleQuestions: (String) -> Unit,
    onToggleEnforce: (String) -> Unit,
    onMarkAllDone: (String) -> Unit,
    onNextSection: () -> Unit,
    modifier: Modifier = Modifier
) {
    val section = todaySection ?: CurriculumData.allSections.first()
    val progress = sectionProgress[section.id]
    val (startPage, endPage) = CurriculumData.getSectionPageRange(section)
    val pageCount = CurriculumData.getSectionPageCount(section)

    val questions = QuestionBank.getQuestionsBySection(section.id)
    val cards = FlashcardBank.getFlashcardsBySection(section.id)

    val isRead = progress?.isReadCompleted ?: false
    val isQ = progress?.isQuestionsCompleted ?: false
    val isEnforce = progress?.isEnforceCompleted ?: false

    val completedTasksCount = (if (isRead) 1 else 0) + (if (isQ) 1 else 0) + (if (isEnforce) 1 else 0)
    val dayProgressPercent = (completedTasksCount * 100) / 3

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("today_study_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Today Header
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MedicalBlue.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = "DAILY STUDY DISPATCH",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MedicalBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Today's Study Plan",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Follow the workflow: READ → QUESTIONS → ENFORCE",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Section Overview Banner
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Chapter ${section.chapterId} • Section ${section.number}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MedicalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$dayProgressPercent% Done",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (dayProgressPercent == 100) SuccessGreen else MedicalBlue
                        )
                    }

                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Farr Printed Pages: $startPage–$endPage ($pageCount pages)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    LinearProgressIndicator(
                        progress = { dayProgressPercent / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = if (dayProgressPercent == 100) SuccessGreen else MedicalBlue,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round
                    )
                }
            }
        }

        // Step 1: READ
        item {
            StepCard(
                stepNumber = 1,
                title = "READ",
                subtitle = "Farr's Physics Notes & High-Yield Summary",
                detail = "Pages $startPage–$endPage ($pageCount pages to read)",
                icon = Icons.Default.MenuBook,
                iconColor = MedicalBlue,
                isCompleted = isRead,
                actionLabel = "Open Reading",
                onToggleCheck = { onToggleRead(section.id) },
                onAction = { onOpenSection(section.chapterId, section.id, SectionStage.READ) }
            )
        }

        // Step 2: QUESTIONS
        item {
            StepCard(
                stepNumber = 2,
                title = "QUESTIONS",
                subtitle = "FRCR Physics Question Bank",
                detail = "${questions.size} practice questions mapped to this section",
                icon = Icons.Default.Quiz,
                iconColor = MedicalAmber,
                isCompleted = isQ,
                actionLabel = "Solve Questions",
                onToggleCheck = { onToggleQuestions(section.id) },
                onAction = { onOpenSection(section.chapterId, section.id, SectionStage.QUESTIONS) }
            )
        }

        // Step 3: ENFORCE
        item {
            StepCard(
                stepNumber = 3,
                title = "ENFORCE",
                subtitle = "Flashcards & Spaced Recall",
                detail = "${cards.size} high-yield Anki flashcards for active recall",
                icon = Icons.Default.Style,
                iconColor = MedicalTeal,
                isCompleted = isEnforce,
                actionLabel = "Review Deck",
                onToggleCheck = { onToggleEnforce(section.id) },
                onAction = { onOpenSection(section.chapterId, section.id, SectionStage.ENFORCE) }
            )
        }

        // Completion & Advance
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (dayProgressPercent == 100) {
                    Button(
                        onClick = onNextSection,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("advance_next_section_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Section Complete! Next Section →", fontWeight = FontWeight.Bold)
                    }
                } else {
                    OutlinedButton(
                        onClick = { onMarkAllDone(section.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("mark_today_all_done_button"),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Mark All 3 Steps Complete")
                    }
                }
            }
        }
    }
}

@Composable
fun StepCard(
    stepNumber: Int,
    title: String,
    subtitle: String,
    detail: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    isCompleted: Boolean,
    actionLabel: String,
    onToggleCheck: () -> Unit,
    onAction: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) SuccessGreen.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { onToggleCheck() },
                colors = CheckboxDefaults.colors(checkedColor = SuccessGreen)
            )

            Spacer(Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "$stepNumber. $title",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (isCompleted) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = SuccessGreen.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "Done",
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = SuccessGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = detail,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.width(8.dp))

            FilledTonalButton(
                onClick = onAction,
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(actionLabel, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
