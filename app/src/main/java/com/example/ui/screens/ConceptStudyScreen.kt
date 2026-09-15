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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumData
import com.example.data.local.ConceptMasteryEntity
import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.Flashcard
import com.example.data.model.PrimeContent
import com.example.data.model.Question
import com.example.data.model.QuestionType
import com.example.ui.components.InteractiveDiagramSelector
import com.example.ui.components.PaceHeader
import com.example.ui.components.RevisionTableComposable
import com.example.ui.navigation.PaceStage
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.PaceAcquireColor
import com.example.ui.theme.PaceChallengeColor
import com.example.ui.theme.PaceEnforceColor
import com.example.ui.theme.PacePrimeColor
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningAmber

@Composable
fun ConceptStudyScreen(
    conceptId: String,
    currentStage: PaceStage,
    conceptMastery: ConceptMasteryEntity?,
    isBookmarked: Boolean,
    onSelectStage: (PaceStage) -> Unit,
    onCompleteStage: (PaceStage) -> Unit,
    onToggleBookmark: () -> Unit,
    onBackToChapter: () -> Unit,
    // Challenge bindings
    conceptQuestions: List<Question>,
    selectedSbaOption: Int?,
    tfAnswers: Map<Int, Boolean>,
    isQSubmitted: Boolean,
    onSelectSbaOption: (Int) -> Unit,
    onToggleTfStem: (Int, Boolean) -> Unit,
    onSubmitQuestion: (Question) -> Unit,
    // Flashcard bindings
    conceptCards: List<Flashcard>,
    cardIndex: Int,
    isCardFlipped: Boolean,
    onFlipCard: () -> Unit,
    onRateCard: (Flashcard, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val concept = CurriculumData.getConcept(conceptId) ?: return

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("concept_study_screen")
    ) {
        // Top Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackToChapter, modifier = Modifier.testTag("back_button")) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Chapter"
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Section ${concept.sectionId}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = concept.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }

        // PACE Stage Header & Page Reference Banner
        PaceHeader(
            currentStage = currentStage,
            primeCompleted = conceptMastery?.primeCompleted == true,
            acquireCompleted = conceptMastery?.acquireCompleted == true,
            challengeCompleted = (conceptMastery?.questionsAttempted ?: 0) > 0,
            enforceCompleted = (conceptMastery?.flashcardsReviewed ?: 0) > 0,
            onSelectStage = onSelectStage,
            printedPage = concept.printedPage,
            pdfPage = concept.pdfPage,
            isBookmarked = isBookmarked,
            onToggleBookmark = onToggleBookmark,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Content Area based on Stage
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (currentStage) {
                PaceStage.PRIME -> PrimeStageContent(
                    prime = concept.prime,
                    onNext = { onCompleteStage(PaceStage.PRIME) }
                )
                PaceStage.ACQUIRE -> AcquireStageContent(
                    acquire = concept.acquire,
                    onNext = { onCompleteStage(PaceStage.ACQUIRE) }
                )
                PaceStage.CHALLENGE -> ChallengeStageContent(
                    questions = conceptQuestions,
                    selectedOption = selectedSbaOption,
                    tfAnswers = tfAnswers,
                    isSubmitted = isQSubmitted,
                    onSelectOption = onSelectSbaOption,
                    onToggleTf = onToggleTfStem,
                    onSubmit = onSubmitQuestion,
                    onNext = { onCompleteStage(PaceStage.CHALLENGE) }
                )
                PaceStage.ENFORCE -> EnforceStageContent(
                    cards = conceptCards,
                    cardIndex = cardIndex,
                    isFlipped = isCardFlipped,
                    onFlip = onFlipCard,
                    onRate = onRateCard,
                    onFinish = { onCompleteStage(PaceStage.ENFORCE) }
                )
            }
        }
    }
}

// -------------------------------------------------------------
// 1. PRIME STAGE
// -------------------------------------------------------------
@Composable
fun PrimeStageContent(
    prime: PrimeContent,
    onNext: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("prime_content_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // High-Yield Overview Callout
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = PacePrimeColor.copy(alpha = 0.08f)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = PacePrimeColor, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("KEY CONCEPTS", fontWeight = FontWeight.Bold, color = PacePrimeColor, fontSize = 12.sp, letterSpacing = 0.5.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    prime.keyConcepts.forEach { point ->
                        Row(modifier = Modifier.padding(vertical = 3.dp)) {
                            Text("• ", color = PacePrimeColor, fontWeight = FontWeight.Bold)
                            Text(text = point, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                        }
                    }
                }
            }
        }

        // Structured Notes
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Structured Notes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    prime.structuredNotes.forEach { note ->
                        Text(
                            text = note,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Definitions
        if (prime.definitions.isNotEmpty()) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Core Definitions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MedicalTeal)
                        Spacer(modifier = Modifier.height(8.dp))
                        prime.definitions.forEach { def ->
                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Text(def.term, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MedicalBlue)
                                Text(def.definition, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
                            }
                        }
                    }
                }
            }
        }

        // Equations
        if (prime.equations.isNotEmpty()) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Key Mathematical Relationships", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        prime.equations.forEach { eq ->
                            Surface(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(
                                    text = eq,
                                    fontWeight = FontWeight.SemiBold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Revision Tables
        items(prime.tables) { table ->
            RevisionTableComposable(table = table)
        }

        // What You Must Understand (Green Box)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SuccessGreen.copy(alpha = 0.08f))
                    .border(1.dp, SuccessGreen.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Psychology, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("WHAT YOU MUST UNDERSTAND", fontWeight = FontWeight.Bold, color = SuccessGreen, fontSize = 11.sp, letterSpacing = 0.5.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    prime.whatYouMustUnderstand.forEach { pt ->
                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                            Text("✓ ", color = SuccessGreen, fontWeight = FontWeight.Bold)
                            Text(pt, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                        }
                    }
                }
            }
        }

        // What You Should Memorize (Amber Box)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MedicalAmber.copy(alpha = 0.08f))
                    .border(1.dp, MedicalAmber.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = MedicalAmber, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("WHAT YOU SHOULD MEMORIZE", fontWeight = FontWeight.Bold, color = MedicalAmber, fontSize = 11.sp, letterSpacing = 0.5.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    prime.whatYouShouldMemorize.forEach { pt ->
                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                            Text("★ ", color = MedicalAmber, fontWeight = FontWeight.Bold)
                            Text(pt, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                        }
                    }
                }
            }
        }

        // High-Yield Facts
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.LocalFireDepartment, contentDescription = null, tint = ErrorRed, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("HIGH-YIELD FRCR FACTS", fontWeight = FontWeight.Bold, color = ErrorRed, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    prime.highYieldFacts.forEach { fact ->
                        Text("• $fact", style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp, modifier = Modifier.padding(vertical = 3.dp))
                    }
                }
            }
        }

        // Completion & Action Button
        item {
            Button(
                onClick = onNext,
                colors = ButtonDefaults.buttonColors(containerColor = PaceAcquireColor),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("complete_prime_button")
            ) {
                Text("Proceed to Acquire (Mechanism & Simulation)", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// -------------------------------------------------------------
// 2. ACQUIRE STAGE
// -------------------------------------------------------------
@Composable
fun AcquireStageContent(
    acquire: AcquireContent,
    onNext: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("acquire_content_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Deep Intuition / Simple Explanation
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = PaceAcquireColor.copy(alpha = 0.08f)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, tint = PaceAcquireColor)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("INTUITIVE EXPLANATION", fontWeight = FontWeight.Bold, color = PaceAcquireColor, fontSize = 12.sp, letterSpacing = 0.5.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = acquire.simpleExplanation,
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        // Interactive Physics Simulator
        item {
            InteractiveDiagramSelector(diagramType = acquire.interactiveDiagramType)
        }

        // Step-by-Step Mechanism
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Step-by-Step Mechanism", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    acquire.stepByStepMechanism.forEachIndexed { idx, step ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(PaceAcquireColor.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${idx + 1}",
                                    color = PaceAcquireColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 20.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Why It Happens (Physical Principle)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Why It Happens (Physical Origin)", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MedicalBlue)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = acquire.whyItHappens,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Common Misconception (Warning Banner)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(WarningAmber.copy(alpha = 0.1f))
                    .border(1.dp, WarningAmber.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = WarningAmber, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("COMMON FRCR MISCONCEPTION", fontWeight = FontWeight.Bold, color = WarningAmber, fontSize = 11.sp, letterSpacing = 0.5.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = acquire.commonMisconception,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Analogy
        item {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Physics Analogy", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = acquire.analogy,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Clinical Relevance & Image Quality / Dose Impact
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Clinical Relevance & Imaging Protocol", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MedicalTeal)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = acquire.clinicalRelevance, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text("Impact on Image Quality & Patient Dose", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = PaceChallengeColor)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = acquire.imageQualityAndDoseImpact, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                }
            }
        }

        // Proceed Button
        item {
            Button(
                onClick = onNext,
                colors = ButtonDefaults.buttonColors(containerColor = PaceChallengeColor),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("complete_acquire_button")
            ) {
                Text("Test Knowledge in Challenge (FRCR MCQs)", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// -------------------------------------------------------------
// 3. CHALLENGE STAGE
// -------------------------------------------------------------
@Composable
fun ChallengeStageContent(
    questions: List<Question>,
    selectedOption: Int?,
    tfAnswers: Map<Int, Boolean>,
    isSubmitted: Boolean,
    onSelectOption: (Int) -> Unit,
    onToggleTf: (Int, Boolean) -> Unit,
    onSubmit: (Question) -> Unit,
    onNext: () -> Unit
) {
    if (questions.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Reviewing Challenge Questions", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onNext) {
                    Text("Proceed to Flashcards (Enforce)")
                }
            }
        }
        return
    }

    val question = questions.first()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("challenge_content_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Question Header Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = PaceChallengeColor.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (question.type == QuestionType.SINGLE_BEST_ANSWER) "SINGLE BEST ANSWER (SBA)" else "TRUE / FALSE (5-STEM)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = PaceChallengeColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Text(
                            text = question.difficulty,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = question.stem,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        // Options: SBA or True/False 5-stem
        if (question.type == QuestionType.SINGLE_BEST_ANSWER) {
            items(question.options.indices.toList()) { idx ->
                val optionText = question.options[idx]
                val isSelected = selectedOption == idx
                val isCorrect = idx == question.correctAnswerIndex

                val borderColor = when {
                    !isSubmitted && isSelected -> MaterialTheme.colorScheme.primary
                    isSubmitted && isCorrect -> SuccessGreen
                    isSubmitted && isSelected && !isCorrect -> ErrorRed
                    else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
                }

                val containerColor = when {
                    !isSubmitted && isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                    isSubmitted && isCorrect -> SuccessGreen.copy(alpha = 0.12f)
                    isSubmitted && isSelected && !isCorrect -> ErrorRed.copy(alpha = 0.12f)
                    else -> MaterialTheme.colorScheme.surface
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                        .clickable(enabled = !isSubmitted) { onSelectOption(idx) }
                        .testTag("sba_option_$idx"),
                    color = containerColor
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isSelected || (isSubmitted && isCorrect)) FontWeight.SemiBold else FontWeight.Normal,
                            modifier = Modifier.weight(1f),
                            lineHeight = 20.sp
                        )

                        if (isSubmitted) {
                            if (isCorrect) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Correct", tint = SuccessGreen)
                            } else if (isSelected) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "Incorrect", tint = ErrorRed)
                            }
                        }
                    }
                }
            }
        } else {
            // True / False 5-stem Question
            items(question.options.indices.toList()) { idx ->
                val stemText = question.options[idx]
                val userChoice = tfAnswers[idx]
                val actualTruth = question.tfAnswers.getOrNull(idx)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = stemText, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                FilterChip(
                                    selected = userChoice == true,
                                    onClick = { onToggleTf(idx, true) },
                                    label = { Text("TRUE") },
                                    enabled = !isSubmitted,
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = SuccessGreen.copy(alpha = 0.2f),
                                        selectedLabelColor = SuccessGreen
                                    )
                                )
                                FilterChip(
                                    selected = userChoice == false,
                                    onClick = { onToggleTf(idx, false) },
                                    label = { Text("FALSE") },
                                    enabled = !isSubmitted,
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = ErrorRed.copy(alpha = 0.2f),
                                        selectedLabelColor = ErrorRed
                                    )
                                )
                            }

                            if (isSubmitted && actualTruth != null) {
                                val isUserRight = userChoice == actualTruth
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (isUserRight) Icons.Default.Check else Icons.Default.Close,
                                        contentDescription = null,
                                        tint = if (isUserRight) SuccessGreen else ErrorRed,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (actualTruth) "TRUE" else "FALSE",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = if (actualTruth) SuccessGreen else ErrorRed
                                    )
                                }
                            }
                        }

                        // Explanation for this stem
                        if (isSubmitted && question.tfExplanations.getOrNull(idx) != null) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = question.tfExplanations[idx],
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Submit & Explanation Reveal
        item {
            if (!isSubmitted) {
                val canSubmit = if (question.type == QuestionType.SINGLE_BEST_ANSWER) {
                    selectedOption != null
                } else {
                    tfAnswers.size == question.options.size
                }

                Button(
                    onClick = { onSubmit(question) },
                    enabled = canSubmit,
                    colors = ButtonDefaults.buttonColors(containerColor = PaceChallengeColor),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("submit_challenge_button")
                ) {
                    Text("Submit Answers", fontWeight = FontWeight.Bold)
                }
            } else {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Comprehensive Explanation",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = question.detailedExplanation,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Source: ${question.sourceChapter} • ${question.sourceSection} (Print p. ${question.printedPage})",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(containerColor = PaceEnforceColor),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("proceed_to_enforce_button")
                ) {
                    Text("Proceed to Flashcards (Enforce)", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// -------------------------------------------------------------
// 4. ENFORCE STAGE
// -------------------------------------------------------------
@Composable
fun EnforceStageContent(
    cards: List<Flashcard>,
    cardIndex: Int,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onRate: (Flashcard, String, Int) -> Unit,
    onFinish: () -> Unit
) {
    if (cards.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("All flashcards mastered!", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onFinish) {
                    Text("Finish Concept Study")
                }
            }
        }
        return
    }

    val currentCard = cards.getOrNull(cardIndex) ?: cards.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("enforce_content"),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Counter & Category
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = PaceEnforceColor.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = currentCard.category,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = PaceEnforceColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Text(
                text = "Card ${cardIndex + 1} of ${cards.size}",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Flip Flashcard Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onFlip)
                .testTag("interactive_flashcard"),
            colors = CardDefaults.cardColors(
                containerColor = if (isFlipped) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (isFlipped) "ANSWER & KEY RECALL POINTS" else "QUESTION (TAP TO FLIP)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isFlipped) PaceAcquireColor else PaceEnforceColor,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (!isFlipped) {
                        Text(
                            text = currentCard.question,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 30.sp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    } else {
                        Text(
                            text = currentCard.answer,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(modifier = Modifier.fillMaxWidth()) {
                            currentCard.keyPoints.forEach { pt ->
                                Text(
                                    text = "• $pt",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 18.sp,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Farr p. ${currentCard.printedPage} / PDF ${currentCard.pdfPage}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Spaced Repetition Rating Buttons
        if (isFlipped) {
            Column {
                Text(
                    text = "Rate Confidence for Spaced Repetition:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    RatingButton("Again", "1d", ErrorRed, Modifier.weight(1f)) {
                        onRate(currentCard, "AGAIN", cards.size)
                    }
                    RatingButton("Hard", "2d", WarningAmber, Modifier.weight(1f)) {
                        onRate(currentCard, "HARD", cards.size)
                    }
                    RatingButton("Good", "3d", MedicalBlue, Modifier.weight(1f)) {
                        onRate(currentCard, "GOOD", cards.size)
                    }
                    RatingButton("Easy", "5d", SuccessGreen, Modifier.weight(1f)) {
                        onRate(currentCard, "EASY", cards.size)
                    }
                }
            }
        } else {
            Button(
                onClick = onFlip,
                colors = ButtonDefaults.buttonColors(containerColor = PaceEnforceColor),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("flip_card_button")
            ) {
                Icon(imageVector = Icons.Default.RotateRight, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tap Card to Flip & View Answer", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun RatingButton(
    label: String,
    interval: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(label, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(interval, fontSize = 10.sp, color = color.copy(alpha = 0.8f))
        }
    }
}
