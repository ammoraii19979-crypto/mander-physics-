package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumData
import com.example.data.content.QuestionBank
import com.example.data.model.Question
import com.example.data.model.QuestionType
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.PaceChallengeColor
import com.example.ui.theme.SuccessGreen

@Composable
fun QuestionBankScreen(
    selectedChapterFilter: Int?,
    questionIndex: Int,
    selectedSbaOption: Int?,
    tfAnswers: Map<Int, Boolean>,
    isSubmitted: Boolean,
    onSelectChapterFilter: (Int?) -> Unit,
    onNextQuestion: (Int) -> Unit,
    onPrevQuestion: () -> Unit,
    onSelectSbaOption: (Int) -> Unit,
    onToggleTfStem: (Int, Boolean) -> Unit,
    onSubmitQuestion: (Question) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredQuestions = if (selectedChapterFilter == null) {
        QuestionBank.allQuestions
    } else {
        QuestionBank.getQuestionsByChapter(selectedChapterFilter)
    }

    val currentQuestion = filteredQuestions.getOrNull(questionIndex) ?: filteredQuestions.firstOrNull()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("qbank_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Top Filter Bar
        item {
            Column {
                Text(
                    text = "FRCR Physics Question Bank",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        FilterChip(
                            selected = selectedChapterFilter == null,
                            onClick = { onSelectChapterFilter(null) },
                            label = { Text("All Chapters (${QuestionBank.allQuestions.size})") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PaceChallengeColor.copy(alpha = 0.15f),
                                selectedLabelColor = PaceChallengeColor
                            )
                        )
                    }
                    items((1..10).toList()) { ch ->
                        val count = QuestionBank.getQuestionsByChapter(ch).size
                        FilterChip(
                            selected = selectedChapterFilter == ch,
                            onClick = { onSelectChapterFilter(ch) },
                            label = { Text("Ch $ch ($count)") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PaceChallengeColor.copy(alpha = 0.15f),
                                selectedLabelColor = PaceChallengeColor
                            )
                        )
                    }
                }
            }
        }

        if (currentQuestion == null) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No questions in this section.")
                }
            }
        } else {
            // Question Progress & Controls
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Question ${questionIndex + 1} of ${filteredQuestions.size}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row {
                        IconButton(
                            onClick = onPrevQuestion,
                            enabled = questionIndex > 0
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                        }
                        IconButton(
                            onClick = { onNextQuestion(filteredQuestions.size) },
                            enabled = questionIndex + 1 < filteredQuestions.size
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                        }
                    }
                }
            }

            // Question Stem Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
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
                                    text = if (currentQuestion.type == QuestionType.SINGLE_BEST_ANSWER) "SINGLE BEST ANSWER" else "TRUE / FALSE 5-STEM",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = PaceChallengeColor,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                            Text(
                                text = "Print p. ${currentQuestion.printedPage} / PDF ${currentQuestion.pdfPage}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = currentQuestion.stem,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp
                        )
                    }
                }
            }

            // Options: SBA or TF
            if (currentQuestion.type == QuestionType.SINGLE_BEST_ANSWER) {
                itemsIndexed(currentQuestion.options) { idx, optionText ->
                    val isSelected = selectedSbaOption == idx
                    val isCorrect = idx == currentQuestion.correctAnswerIndex

                    val borderColor = when {
                        !isSubmitted && isSelected -> MaterialTheme.colorScheme.primary
                        isSubmitted && isCorrect -> SuccessGreen
                        isSubmitted && isSelected && !isCorrect -> ErrorRed
                        else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
                    }

                    val containerColor = when {
                        !isSubmitted && isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                        isSubmitted && isCorrect -> SuccessGreen.copy(alpha = 0.12f)
                        isSubmitted && isSelected && !isCorrect -> ErrorRed.copy(alpha = 0.12f)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                            .clickable(enabled = !isSubmitted) { onSelectSbaOption(idx) },
                        color = containerColor
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyMedium,
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
                itemsIndexed(currentQuestion.options) { idx, stemText ->
                    val userChoice = tfAnswers[idx]
                    val actualTruth = currentQuestion.tfAnswers.getOrNull(idx)

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        shape = RoundedCornerShape(12.dp)
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
                                        onClick = { onToggleTfStem(idx, true) },
                                        label = { Text("TRUE") },
                                        enabled = !isSubmitted,
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = SuccessGreen.copy(alpha = 0.2f),
                                            selectedLabelColor = SuccessGreen
                                        )
                                    )
                                    FilterChip(
                                        selected = userChoice == false,
                                        onClick = { onToggleTfStem(idx, false) },
                                        label = { Text("FALSE") },
                                        enabled = !isSubmitted,
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = ErrorRed.copy(alpha = 0.2f),
                                            selectedLabelColor = ErrorRed
                                        )
                                    )
                                }

                                if (isSubmitted && actualTruth != null) {
                                    val isRight = userChoice == actualTruth
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = if (isRight) Icons.Default.Check else Icons.Default.Close,
                                            contentDescription = null,
                                            tint = if (isRight) SuccessGreen else ErrorRed,
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

                            if (isSubmitted && currentQuestion.tfExplanations.getOrNull(idx) != null) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = currentQuestion.tfExplanations[idx],
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // Submission & Detailed Explanations
            item {
                if (!isSubmitted) {
                    val canSubmit = if (currentQuestion.type == QuestionType.SINGLE_BEST_ANSWER) {
                        selectedSbaOption != null
                    } else {
                        tfAnswers.size == currentQuestion.options.size
                    }

                    Button(
                        onClick = { onSubmitQuestion(currentQuestion) },
                        enabled = canSubmit,
                        colors = ButtonDefaults.buttonColors(containerColor = PaceChallengeColor),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Check Answer", fontWeight = FontWeight.Bold)
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
                                text = currentQuestion.detailedExplanation,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Farr's Physics • ${currentQuestion.sourceChapter} (Print p. ${currentQuestion.printedPage} / PDF ${currentQuestion.pdfPage})",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (questionIndex + 1 < filteredQuestions.size) {
                        Button(
                            onClick = { onNextQuestion(filteredQuestions.size) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text("Next Question", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
