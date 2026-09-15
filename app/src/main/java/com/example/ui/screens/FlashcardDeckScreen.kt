package com.example.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.RotateRight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.FlashcardBank
import com.example.data.model.Flashcard
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.PaceAcquireColor
import com.example.ui.theme.PaceEnforceColor
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningAmber

@Composable
fun FlashcardDeckScreen(
    selectedChapterFilter: Int?,
    cardIndex: Int,
    isFlipped: Boolean,
    onSelectChapterFilter: (Int?) -> Unit,
    onFlipCard: () -> Unit,
    onNextCard: (Int) -> Unit,
    onPrevCard: () -> Unit,
    onRateCard: (Flashcard, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredCards = if (selectedChapterFilter == null) {
        FlashcardBank.allFlashcards
    } else {
        FlashcardBank.getFlashcardsByChapter(selectedChapterFilter)
    }

    val currentCard = filteredCards.getOrNull(cardIndex) ?: filteredCards.firstOrNull()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("flashcard_screen"),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Filter Bar
        Column {
            Text(
                text = "Spaced Repetition Flashcards",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                item {
                    FilterChip(
                        selected = selectedChapterFilter == null,
                        onClick = { onSelectChapterFilter(null) },
                        label = { Text("All (${FlashcardBank.allFlashcards.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PaceEnforceColor.copy(alpha = 0.15f),
                            selectedLabelColor = PaceEnforceColor
                        )
                    )
                }
                items((1..10).toList()) { ch ->
                    val count = FlashcardBank.getFlashcardsByChapter(ch).size
                    FilterChip(
                        selected = selectedChapterFilter == ch,
                        onClick = { onSelectChapterFilter(ch) },
                        label = { Text("Ch $ch ($count)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PaceEnforceColor.copy(alpha = 0.15f),
                            selectedLabelColor = PaceEnforceColor
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (currentCard != null) {
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

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Card ${cardIndex + 1} / ${filteredCards.size}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(onClick = onPrevCard, enabled = cardIndex > 0) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Prev")
                        }
                        IconButton(onClick = { onNextCard(filteredCards.size) }, enabled = cardIndex + 1 < filteredCards.size) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                        }
                    }
                }
            }
        }

        // Active Flashcard
        if (currentCard == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No flashcards found for this selection.")
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable(onClick = onFlipCard)
                    .testTag("deck_flashcard"),
                colors = CardDefaults.cardColors(
                    containerColor = if (isFlipped) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isFlipped) "ANSWER & CLINICAL POINTS" else "QUESTION (TAP TO FLIP)",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isFlipped) PaceAcquireColor else PaceEnforceColor,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        if (!isFlipped) {
                            Text(
                                text = currentCard.question,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 32.sp
                            )
                        } else {
                            Text(
                                text = currentCard.answer,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
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

                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Farr's Physics • Print p. ${currentCard.printedPage} / PDF ${currentCard.pdfPage}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Rating or Flip Button
            if (isFlipped) {
                Column {
                    Text(
                        text = "Confidence Rating (Spaced Repetition):",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        SpacedButton("Again", "1d", ErrorRed, Modifier.weight(1f)) {
                            onRateCard(currentCard, "AGAIN", filteredCards.size)
                        }
                        SpacedButton("Hard", "2d", WarningAmber, Modifier.weight(1f)) {
                            onRateCard(currentCard, "HARD", filteredCards.size)
                        }
                        SpacedButton("Good", "3d", MedicalBlue, Modifier.weight(1f)) {
                            onRateCard(currentCard, "GOOD", filteredCards.size)
                        }
                        SpacedButton("Easy", "5d", SuccessGreen, Modifier.weight(1f)) {
                            onRateCard(currentCard, "EASY", filteredCards.size)
                        }
                    }
                }
            } else {
                Button(
                    onClick = onFlipCard,
                    colors = ButtonDefaults.buttonColors(containerColor = PaceEnforceColor),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Icon(imageVector = Icons.Default.RotateRight, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Flip Card to Reveal Answer", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun SpacedButton(
    title: String,
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
            Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(interval, fontSize = 10.sp, color = color.copy(alpha = 0.8f))
        }
    }
}
