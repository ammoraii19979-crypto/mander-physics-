package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumData
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.Chapter
import com.example.ui.navigation.PaceStage
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.PaceAcquireColor
import com.example.ui.theme.PaceChallengeColor
import com.example.ui.theme.PacePrimeColor
import com.example.ui.theme.SuccessGreen
import kotlin.math.roundToInt

@Composable
fun CurriculumScreen(
    userProgress: UserProgressEntity?,
    conceptMasteries: Map<String, ConceptMasteryEntity>,
    questionAttempts: List<QuestionAttemptEntity> = emptyList(),
    onSelectChapter: (Int) -> Unit,
    onResumeStudy: (String, PaceStage) -> Unit,
    onViewProgress: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val chapters = CurriculumData.chapters

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("curriculum_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Trainee Mastery Circular Progress Indicator
        item {
            TraineeMasteryCard(
                conceptMasteries = conceptMasteries,
                questionAttempts = questionAttempts,
                onViewProgress = onViewProgress
            )
        }

        // Hero / Resume Study Card
        item {
            ResumeStudyHero(
                userProgress = userProgress,
                onResume = onResumeStudy
            )
        }

        // Section Title & Curriculum Overview
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "FRCR Physics Curriculum",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "10 Chapters  •  209 Pages",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // 10 Chapters List
        items(chapters) { chapter ->
            val chapterConcepts = chapter.sections.flatMap { it.concepts }
            val completedCount = chapterConcepts.count { c ->
                val m = conceptMasteries[c.id]
                m != null && m.masteryScore >= 50
            }
            val progressPercent = if (chapterConcepts.isNotEmpty()) {
                completedCount.toFloat() / chapterConcepts.size.toFloat()
            } else 0f

            ChapterCard(
                chapter = chapter,
                conceptCount = chapterConcepts.size,
                completedCount = completedCount,
                progressPercent = progressPercent,
                onClick = { onSelectChapter(chapter.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ResumeStudyHero(
    userProgress: UserProgressEntity?,
    onResume: (String, PaceStage) -> Unit
) {
    val conceptId = userProgress?.currentConceptId ?: "1.1.1"
    val concept = CurriculumData.getConcept(conceptId) ?: CurriculumData.getAllConcepts().first()
    val stageName = userProgress?.currentStage ?: "PRIME"
    val stage = try {
        PaceStage.valueOf(stageName)
    } catch (e: Exception) {
        PaceStage.PRIME
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("resume_study_hero"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "CONTINUE PACE STUDY",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 0.5.sp
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Stage: ${stage.title}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = concept.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "Chapter ${concept.chapterId} • Section ${concept.sectionId} • Print p. ${concept.printedPage} | PDF p. ${concept.pdfPage}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { onResume(conceptId, stage) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("resume_study_button")
            ) {
                Text("Resume Study (${stage.title})", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun ChapterCard(
    chapter: Chapter,
    conceptCount: Int,
    completedCount: Int,
    progressPercent: Float,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag("chapter_card_${chapter.number}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Chapter Number Badge
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(getChapterAccentColor(chapter.number).copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = getChapterIcon(chapter.number),
                        contentDescription = null,
                        tint = getChapterAccentColor(chapter.number),
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CHAPTER ${chapter.number}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = getChapterAccentColor(chapter.number),
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = chapter.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open Chapter",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = chapter.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Page references & concepts count badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Print p. ${chapter.printStartPage}+  •  PDF p. ${chapter.pdfStartPage}+",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "$completedCount / $conceptCount Concepts",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = if (completedCount == conceptCount && conceptCount > 0) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { progressPercent },
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

fun getChapterAccentColor(chapterNumber: Int): Color {
    return when (chapterNumber) {
        1 -> PacePrimeColor
        2 -> Color(0xFFE11D48)
        3 -> MedicalAmber
        4 -> Color(0xFF0284C7)
        5 -> MedicalTeal
        6 -> Color(0xFFD97706)
        7 -> Color(0xFF2563EB)
        8 -> Color(0xFF0D9488)
        9 -> Color(0xFF7C3AED)
        10 -> Color(0xFF4F46E5)
        else -> MedicalTeal
    }
}

fun getChapterIcon(chapterNumber: Int): ImageVector {
    return when (chapterNumber) {
        1 -> Icons.Default.ElectricBolt
        2 -> Icons.Default.Shield
        3 -> Icons.AutoMirrored.Filled.MenuBook
        4 -> Icons.Default.ViewInAr
        5 -> Icons.Default.ViewInAr
        6 -> Icons.Default.Videocam
        7 -> Icons.Default.ViewInAr
        8 -> Icons.Default.Waves
        9 -> Icons.Default.ViewInAr
        10 -> Icons.Default.ElectricBolt
        else -> Icons.AutoMirrored.Filled.MenuBook
    }
}

@Composable
fun TraineeMasteryCard(
    conceptMasteries: Map<String, ConceptMasteryEntity>,
    questionAttempts: List<QuestionAttemptEntity>,
    onViewProgress: () -> Unit,
    modifier: Modifier = Modifier
) {
    val allConcepts = CurriculumData.getAllConcepts()
    val totalConcepts = allConcepts.size
    val masteredConcepts = allConcepts.count { c ->
        val m = conceptMasteries[c.id]
        m != null && (m.masteryScore >= 50 || (m.primeCompleted && m.acquireCompleted))
    }

    val completedChapters = CurriculumData.chapters.count { ch ->
        val chConcepts = ch.sections.flatMap { it.concepts }
        chConcepts.isNotEmpty() && chConcepts.all { c ->
            val m = conceptMasteries[c.id]
            m != null && (m.masteryScore >= 50 || (m.primeCompleted && m.acquireCompleted))
        }
    }

    val curriculumRatio = if (totalConcepts > 0) masteredConcepts.toFloat() / totalConcepts.toFloat() else 0f
    val curriculumPercent = (curriculumRatio * 100f).roundToInt()

    val totalQuestions = questionAttempts.size
    val correctQuestions = questionAttempts.count { it.isCorrect }
    val quizAccuracyRatio = if (totalQuestions > 0) correctQuestions.toFloat() / totalQuestions.toFloat() else 0f
    val quizAccuracyPercent = (quizAccuracyRatio * 100f).roundToInt()

    // Composite Trainee Mastery across completed chapters and quiz performance
    val masteryRatio = if (totalQuestions > 0) {
        (curriculumRatio * 0.55f + quizAccuracyRatio * 0.45f).coerceIn(0f, 1f)
    } else {
        curriculumRatio.coerceIn(0f, 1f)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = masteryRatio,
        animationSpec = tween(durationMillis = 800),
        label = "trainee_mastery_progress"
    )

    val progressColor = when {
        masteryRatio >= 0.75f -> SuccessGreen
        masteryRatio >= 0.40f -> MedicalBlue
        else -> PaceAcquireColor
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("trainee_mastery_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "TRAINEE READINESS & MASTERY",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 0.5.sp
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .clickable(onClick = onViewProgress)
                        .testTag("view_analytics_badge")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Analytics",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Body: Circular Progress Indicator on Left + Detailed Chapter & Quiz Breakdown on Right
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circular Progress Indicator
                Box(
                    modifier = Modifier
                        .size(106.dp)
                        .testTag("circular_progress_container"),
                    contentAlignment = Alignment.Center
                ) {
                    // Background Track
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        strokeWidth = 9.dp
                    )

                    // Active Arc
                    CircularProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("circular_progress_indicator"),
                        color = progressColor,
                        strokeWidth = 9.dp,
                        strokeCap = StrokeCap.Round
                    )

                    // Center Labels
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${(animatedProgress * 100f).roundToInt()}%",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "MASTERY",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Detailed Dual Breakdown
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // 1. Chapter & Concept Mastery
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Curriculum Chapters",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "$completedChapters/10 Ch ($curriculumPercent%)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { curriculumRatio },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp)
                                .clip(CircleShape),
                            color = MedicalTeal,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    // 2. Quiz Performance
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "FRCR Quiz Score",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = if (totalQuestions > 0) "$quizAccuracyPercent% ($correctQuestions/$totalQuestions)" else "Not Started",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = PaceChallengeColor
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { if (totalQuestions > 0) quizAccuracyRatio else 0f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp)
                                .clip(CircleShape),
                            color = PaceChallengeColor,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    // Status Pill
                    Surface(
                        color = progressColor.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = when {
                                masteryRatio >= 0.75f -> "Exam Ready • High Mastery"
                                masteryRatio >= 0.35f -> "On Track • Keep Practicing"
                                else -> "Foundational • Complete PACE"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = progressColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}

