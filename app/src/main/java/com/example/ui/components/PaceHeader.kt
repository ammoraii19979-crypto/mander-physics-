package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.navigation.PaceStage
import com.example.ui.theme.PaceAcquireColor
import com.example.ui.theme.PaceChallengeColor
import com.example.ui.theme.PaceEnforceColor
import com.example.ui.theme.PacePrimeColor
import com.example.ui.theme.SuccessGreen

@Composable
fun PaceHeader(
    currentStage: PaceStage,
    primeCompleted: Boolean,
    acquireCompleted: Boolean,
    challengeCompleted: Boolean,
    enforceCompleted: Boolean,
    onSelectStage: (PaceStage) -> Unit,
    printedPage: Int,
    pdfPage: Int,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Book & Page Reference Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = "Farr's Physics Book Reference",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Farr's Physics 2nd Ed.  •  Print p. $printedPage  •  PDF p. $pdfPage",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier
                        .size(32.dp)
                        .testTag("toggle_bookmark_button")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // PACE 4-Stage Selector Pills
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PaceStagePill(
                    stage = PaceStage.PRIME,
                    isSelected = currentStage == PaceStage.PRIME,
                    isCompleted = primeCompleted,
                    accentColor = PacePrimeColor,
                    onClick = { onSelectStage(PaceStage.PRIME) },
                    modifier = Modifier.weight(1f)
                )
                PaceStagePill(
                    stage = PaceStage.ACQUIRE,
                    isSelected = currentStage == PaceStage.ACQUIRE,
                    isCompleted = acquireCompleted,
                    accentColor = PaceAcquireColor,
                    onClick = { onSelectStage(PaceStage.ACQUIRE) },
                    modifier = Modifier.weight(1f)
                )
                PaceStagePill(
                    stage = PaceStage.CHALLENGE,
                    isSelected = currentStage == PaceStage.CHALLENGE,
                    isCompleted = challengeCompleted,
                    accentColor = PaceChallengeColor,
                    onClick = { onSelectStage(PaceStage.CHALLENGE) },
                    modifier = Modifier.weight(1f)
                )
                PaceStagePill(
                    stage = PaceStage.ENFORCE,
                    isSelected = currentStage == PaceStage.ENFORCE,
                    isCompleted = enforceCompleted,
                    accentColor = PaceEnforceColor,
                    onClick = { onSelectStage(PaceStage.ENFORCE) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun PaceStagePill(
    stage: PaceStage,
    isSelected: Boolean,
    isCompleted: Boolean,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) accentColor else MaterialTheme.colorScheme.surface,
        label = "pill_bg"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
        label = "pill_content"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .testTag("pace_pill_${stage.code}"),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Stage Code Badge Circle
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color.White.copy(alpha = 0.25f)
                        else accentColor.copy(alpha = 0.15f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted && !isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = SuccessGreen,
                        modifier = Modifier.size(12.dp)
                    )
                } else {
                    Text(
                        text = stage.code,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else accentColor
                    )
                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stage.title,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = contentColor
            )
        }
    }
}
