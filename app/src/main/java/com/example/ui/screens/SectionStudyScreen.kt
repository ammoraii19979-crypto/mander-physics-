package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumData
import com.example.data.content.FlashcardBank
import com.example.data.content.QuestionBank
import com.example.data.local.BookmarkEntity
import com.example.data.local.SectionProgressEntity
import com.example.data.model.Flashcard
import com.example.data.model.Question
import com.example.data.model.QuestionType
import com.example.data.model.Section
import com.example.ui.navigation.SectionStage
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.SuccessGreen

@Composable
fun SectionStudyScreen(
    chapterId: Int,
    sectionId: String,
    currentStage: SectionStage,
    sectionProgress: SectionProgressEntity?,
    bookmarks: List<BookmarkEntity>,
    onSelectStage: (SectionStage) -> Unit,
    onToggleRead: (String) -> Unit,
    onToggleQuestions: (String) -> Unit,
    onToggleEnforce: (String) -> Unit,
    onMarkAllComplete: (String) -> Unit,
    onToggleBookmark: (String, String, String, String, String) -> Unit,
    onPrevSection: () -> Unit,
    onNextSection: () -> Unit,
    onBack: () -> Unit,
    // Question interaction
    questionIndex: Int,
    selectedOption: Int?,
    tfAnswers: Map<Int, Boolean>,
    isQSubmitted: Boolean,
    onSelectOption: (Int) -> Unit,
    onToggleTf: (Int, Boolean) -> Unit,
    onSubmitQuestion: (Question) -> Unit,
    onNextQuestion: (Int) -> Unit,
    onPrevQuestion: () -> Unit,
    // Flashcard interaction
    cardIndex: Int,
    isCardFlipped: Boolean,
    onFlipCard: () -> Unit,
    onRateCard: (Flashcard, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val section = CurriculumData.getSection(chapterId, sectionId) ?: CurriculumData.allSections.first()
    val chapter = CurriculumData.getChapter(chapterId)
    val (startPage, endPage) = CurriculumData.getSectionPageRange(section)
    val pageCount = CurriculumData.getSectionPageCount(section)

    val questions = QuestionBank.getQuestionsBySection(section.id)
    val cards = FlashcardBank.getFlashcardsBySection(section.id)

    val isRead = sectionProgress?.isReadCompleted ?: false
    val isQ = sectionProgress?.isQuestionsCompleted ?: false
    val isEnforce = sectionProgress?.isEnforceCompleted ?: false
    val isAllComplete = isRead && isQ && isEnforce

    val isBookmarked = bookmarks.any { it.id == "section_${section.id}" }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("section_study_screen")
    ) {
        // Top App Bar
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Chapter $chapterId • Section ${section.number}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MedicalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = section.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }

                    IconButton(
                        onClick = {
                            onToggleBookmark(
                                "section_${section.id}",
                                "SECTION",
                                "${section.number} ${section.title}",
                                "Farr Ch. $chapterId • pp. $startPage–$endPage",
                                section.id
                            )
                        }
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) MedicalBlue else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Page Reference Sub-bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Farr Printed Pages: $startPage–$endPage ($pageCount pp)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (isAllComplete) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SuccessGreen.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "Section Complete ✅",
                                color = SuccessGreen,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(Modifier.height(4.dp))

                // Segmented Tabs: READ | QUESTIONS | ENFORCE
                TabRow(
                    selectedTabIndex = currentStage.ordinal,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MedicalBlue
                ) {
                    Tab(
                        selected = currentStage == SectionStage.READ,
                        onClick = { onSelectStage(SectionStage.READ) },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("READ")
                                if (isRead) Icon(Icons.Default.Check, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(14.dp))
                            }
                        }
                    )
                    Tab(
                        selected = currentStage == SectionStage.QUESTIONS,
                        onClick = { onSelectStage(SectionStage.QUESTIONS) },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("QUESTIONS (${questions.size})")
                                if (isQ) Icon(Icons.Default.Check, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(14.dp))
                            }
                        }
                    )
                    Tab(
                        selected = currentStage == SectionStage.ENFORCE,
                        onClick = { onSelectStage(SectionStage.ENFORCE) },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("ENFORCE (${cards.size})")
                                if (isEnforce) Icon(Icons.Default.Check, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(14.dp))
                            }
                        }
                    )
                }
            }
        }

        // Body Content by Stage
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (currentStage) {
                SectionStage.READ -> {
                    ReadStageContent(
                        section = section,
                        startPage = startPage,
                        endPage = endPage,
                        isRead = isRead,
                        onToggleRead = { onToggleRead(section.id) },
                        onProceedToQuestions = { onSelectStage(SectionStage.QUESTIONS) }
                    )
                }
                SectionStage.QUESTIONS -> {
                    QuestionsStageContent(
                        questions = questions,
                        questionIndex = questionIndex,
                        selectedOption = selectedOption,
                        tfAnswers = tfAnswers,
                        isSubmitted = isQSubmitted,
                        isQuestionsCompleted = isQ,
                        onSelectOption = onSelectOption,
                        onToggleTf = onToggleTf,
                        onSubmitQuestion = onSubmitQuestion,
                        onNextQuestion = onNextQuestion,
                        onPrevQuestion = onPrevQuestion,
                        onToggleQuestionsComplete = { onToggleQuestions(section.id) },
                        onProceedToEnforce = { onSelectStage(SectionStage.ENFORCE) }
                    )
                }
                SectionStage.ENFORCE -> {
                    EnforceStageContent(
                        cards = cards,
                        cardIndex = cardIndex,
                        isFlipped = isCardFlipped,
                        isEnforceCompleted = isEnforce,
                        onFlipCard = onFlipCard,
                        onRateCard = onRateCard,
                        onToggleEnforceComplete = { onToggleEnforce(section.id) },
                        onCompleteAll = { onMarkAllComplete(section.id) }
                    )
                }
            }
        }

        // Bottom Navigation: Previous Section / Next Section
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 3.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onPrevSection,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Prev Section", fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = onNextSection,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Next Section", fontSize = 12.sp)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun ReadStageContent(
    section: Section,
    startPage: Int,
    endPage: Int,
    isRead: Boolean,
    onToggleRead: () -> Unit,
    onProceedToQuestions: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MedicalBlue.copy(alpha = 0.08f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.MenuBook, contentDescription = null, tint = MedicalBlue)
                    Column {
                        Text(
                            text = "Farr's Textbook Reading",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MedicalBlue
                        )
                        Text(
                            text = "Read pages $startPage to $endPage in Farr's Physics for Medical Imaging (2nd Ed). Review the high-yield summaries below.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Concepts in this section
        items(section.concepts, key = { it.id }) { concept ->
            OutlinedCard(
                shape = RoundedCornerShape(16.dp),
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
                            text = concept.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "p. ${concept.printedPage}",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                    // Key Concepts
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "CORE PRINCIPLES",
                            style = MaterialTheme.typography.labelSmall,
                            color = MedicalBlue,
                            fontWeight = FontWeight.Bold
                        )
                        concept.prime.keyConcepts.forEach { point ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("•", color = MedicalBlue, fontWeight = FontWeight.Bold)
                                Text(
                                    text = point,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    // High-Yield Facts
                    if (concept.prime.highYieldFacts.isNotEmpty()) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MedicalAmber.copy(alpha = 0.1f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(Icons.Default.Lightbulb, contentDescription = null, tint = MedicalAmber, modifier = Modifier.size(16.dp))
                                    Text(
                                        text = "HIGH-YIELD FRCR PEARLS",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFC05621)
                                    )
                                }
                                concept.prime.highYieldFacts.forEach { fact ->
                                    Text(
                                        text = "⚡ $fact",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }

                    // Acquire intuition
                    if (concept.acquire.simpleExplanation.isNotBlank()) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "PHYSICAL MECHANISM",
                                style = MaterialTheme.typography.labelSmall,
                                color = MedicalTeal,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = concept.acquire.simpleExplanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Mark Reading Complete Button & Proceed
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onToggleRead,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("mark_reading_complete_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRead) SuccessGreen else MedicalBlue
                    )
                ) {
                    Icon(
                        imageVector = if (isRead) Icons.Default.CheckCircle else Icons.Default.Check,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (isRead) "Reading Marked Complete ☑" else "Mark Reading Complete",
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onProceedToQuestions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Proceed to Practice Questions →", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun QuestionsStageContent(
    questions: List<Question>,
    questionIndex: Int,
    selectedOption: Int?,
    tfAnswers: Map<Int, Boolean>,
    isSubmitted: Boolean,
    isQuestionsCompleted: Boolean,
    onSelectOption: (Int) -> Unit,
    onToggleTf: (Int, Boolean) -> Unit,
    onSubmitQuestion: (Question) -> Unit,
    onNextQuestion: (Int) -> Unit,
    onPrevQuestion: () -> Unit,
    onToggleQuestionsComplete: () -> Unit,
    onProceedToEnforce: () -> Unit
) {
    if (questions.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(Icons.Default.Quiz, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(48.dp))
                Text(
                    text = "No questions specifically mapped to this section yet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(
                    onClick = onProceedToEnforce,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Proceed to Enforce (Flashcards) →")
                }
            }
        }
        return
    }

    val activeQuestion = questions.getOrNull(questionIndex) ?: questions.first()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Counter & Question Type
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MedicalAmber.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "Question ${questionIndex + 1} of ${questions.size}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFC05621),
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = if (activeQuestion.type == QuestionType.SINGLE_BEST_ANSWER) "Single Best Answer" else "True / False Stems",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Question Stem Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = activeQuestion.stem,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 22.sp
                    )

                    HorizontalDivider()

                    // Options list
                    if (activeQuestion.type == QuestionType.SINGLE_BEST_ANSWER) {
                        activeQuestion.options.forEachIndexed { index, optionText ->
                            val isSelected = selectedOption == index
                            val isCorrect = index == activeQuestion.correctAnswerIndex
                            val showFeedback = isSubmitted

                            val bg = when {
                                showFeedback && isCorrect -> SuccessGreen.copy(alpha = 0.12f)
                                showFeedback && isSelected && !isCorrect -> MaterialTheme.colorScheme.error.copy(alpha = 0.12f)
                                isSelected -> MedicalBlue.copy(alpha = 0.1f)
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                            }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = bg,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = !isSubmitted) { onSelectOption(index) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { if (!isSubmitted) onSelectOption(index) }
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = optionText,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    } else {
                        // True/False stems
                        activeQuestion.options.forEachIndexed { index, stemText ->
                            val userAns = tfAnswers[index]
                            val correctAns = activeQuestion.tfAnswers.getOrNull(index)
                            val showFeedback = isSubmitted

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = stemText,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                        FilterChip(
                                            selected = userAns == true,
                                            onClick = { if (!isSubmitted) onToggleTf(index, true) },
                                            label = { Text("T") }
                                        )
                                        FilterChip(
                                            selected = userAns == false,
                                            onClick = { if (!isSubmitted) onToggleTf(index, false) },
                                            label = { Text("F") }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Submit Button
        if (!isSubmitted) {
            item {
                Button(
                    onClick = { onSubmitQuestion(activeQuestion) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("submit_question_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue)
                ) {
                    Text("Check Answer", fontWeight = FontWeight.Bold)
                }
            }
        }

        // Explanation & Textbook Citation (when submitted)
        if (isSubmitted) {
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MedicalTeal.copy(alpha = 0.08f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "DETAILED EXPLANATION",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MedicalTeal
                        )
                        Text(
                            text = activeQuestion.detailedExplanation,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (activeQuestion.printedPage > 0) {
                            Text(
                                text = "Source: Farr's Physics 2nd Ed, p. ${activeQuestion.printedPage}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Next / Prev Question Controls
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = onPrevQuestion,
                        enabled = questionIndex > 0
                    ) {
                        Text("← Prev Q")
                    }

                    if (questionIndex + 1 < questions.size) {
                        Button(
                            onClick = { onNextQuestion(questions.size) },
                            colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue)
                        ) {
                            Text("Next Question →")
                        }
                    } else {
                        Button(
                            onClick = onToggleQuestionsComplete,
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                        ) {
                            Text("Complete All Questions ✅")
                        }
                    }
                }
            }
        }

        // Proceed to Enforce
        item {
            OutlinedButton(
                onClick = onProceedToEnforce,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Proceed to Enforce (Flashcards) →")
            }
        }
    }
}

@Composable
fun EnforceStageContent(
    cards: List<Flashcard>,
    cardIndex: Int,
    isFlipped: Boolean,
    isEnforceCompleted: Boolean,
    onFlipCard: () -> Unit,
    onRateCard: (Flashcard, String, Int) -> Unit,
    onToggleEnforceComplete: () -> Unit,
    onCompleteAll: () -> Unit
) {
    if (cards.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(Icons.Default.Style, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(48.dp))
                Text(
                    text = "No cards specifically mapped to this section yet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(
                    onClick = onCompleteAll,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                ) {
                    Text("Mark Section Complete ✅")
                }
            }
        }
        return
    }

    val activeCard = cards.getOrNull(cardIndex) ?: cards.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Counter
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MedicalTeal.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "Card ${cardIndex + 1} of ${cards.size}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MedicalTeal,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Tap card to reveal answer",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Active Recall Flashcard Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { onFlipCard() }
                .testTag("flashcard_flip_target"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isFlipped) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MedicalBlue.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = if (!isFlipped) "QUESTION / PROMPT" else "ANSWER & KEY PEARLS",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = MedicalBlue,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = if (!isFlipped) activeCard.question else activeCard.answer,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 26.sp
                        )

                        if (isFlipped && activeCard.keyPoints.isNotEmpty()) {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                            Text(
                                text = "CORE RECALL POINTS:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MedicalTeal
                            )
                            activeCard.keyPoints.forEach { point ->
                                Text("• $point", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Farr p. ${activeCard.printedPage}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Flip, contentDescription = null, modifier = Modifier.size(16.dp))
                            Text("Flip", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }

        // Spaced Repetition Rating Buttons
        if (isFlipped) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "RATE YOUR RECALL:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilledTonalButton(
                        onClick = { onRateCard(activeCard, "AGAIN", cards.size) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Again\n1d", fontSize = 11.sp, lineHeight = 13.sp)
                    }
                    FilledTonalButton(
                        onClick = { onRateCard(activeCard, "HARD", cards.size) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Hard\n1d", fontSize = 11.sp, lineHeight = 13.sp)
                    }
                    FilledTonalButton(
                        onClick = { onRateCard(activeCard, "GOOD", cards.size) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Good\n3d", fontSize = 11.sp, lineHeight = 13.sp)
                    }
                    FilledTonalButton(
                        onClick = { onRateCard(activeCard, "EASY", cards.size) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Easy\n5d", fontSize = 11.sp, lineHeight = 13.sp)
                    }
                }
            }
        }

        // Mark Section All Complete Button
        Button(
            onClick = onCompleteAll,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("mark_section_all_complete_button"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Complete Entire Section ✅", fontWeight = FontWeight.Bold)
        }
    }
}
