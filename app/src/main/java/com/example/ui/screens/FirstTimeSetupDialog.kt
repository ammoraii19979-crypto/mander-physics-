package com.example.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.content.CurriculumData
import com.example.data.local.StudyPlanEntity
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.SuccessGreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun FirstTimeSetupDialog(
    initialPlan: StudyPlanEntity?,
    onDismiss: () -> Unit,
    onSavePlan: (targetOption: String, targetDays: Int, daysPerWeek: Int, dailyMinutes: Int, requireAll: Boolean) -> Unit
) {
    var selectedTimeframeOption by remember { mutableStateOf(initialPlan?.targetTimeframeOption ?: "2_MONTHS") }
    var targetDays by remember { mutableIntStateOf(initialPlan?.targetDays ?: 60) }
    var daysPerWeek by remember { mutableIntStateOf(initialPlan?.studyDaysPerWeek ?: 6) }
    var dailyMinutes by remember { mutableIntStateOf(initialPlan?.dailyMinutes ?: 45) }
    var requireAll by remember { mutableStateOf(initialPlan?.requireAllForCompletion ?: true) }

    val totalPages = CurriculumData.TOTAL_BOOK_PAGES // 216
    val totalSections = CurriculumData.allSections.size // 34

    // Live calculations
    val calculatedPagesPerDay = ((totalPages.toDouble() / targetDays) * 10.0).roundToInt() / 10.0
    val calculatedSectionsPerDay = ((totalSections.toDouble() / targetDays) * 10.0).roundToInt() / 10.0
    val estimatedFinishMillis = System.currentTimeMillis() + (targetDays.toLong() * 24 * 60 * 60 * 1000L)
    val finishDateStr = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(Date(estimatedFinishMillis))

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 24.dp)
                .testTag("setup_dialog"),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MedicalBlue.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoGraph,
                                contentDescription = null,
                                tint = MedicalBlue,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Personal Study GPS",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Farr's Physics • 216 Pages • 10 Chapters",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                // Question 1: Target Finish Timeframe
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "1. When do you want to finish the book?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val options = listOf(
                            Triple("2_WEEKS", "2 Weeks", 14),
                            Triple("1_MONTH", "1 Month", 30),
                            Triple("2_MONTHS", "2 Months", 60),
                            Triple("3_MONTHS", "3 Months", 90)
                        )
                        options.forEach { (key, label, days) ->
                            FilterChip(
                                selected = selectedTimeframeOption == key,
                                onClick = {
                                    selectedTimeframeOption = key
                                    targetDays = days
                                },
                                label = { Text(label, fontSize = 12.sp) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Question 2: Study Days per Week
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "2. Study days per week",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "$daysPerWeek days / week",
                            style = MaterialTheme.typography.labelLarge,
                            color = MedicalBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        (3..7).forEach { dayCount ->
                            FilterChip(
                                selected = daysPerWeek == dayCount,
                                onClick = { daysPerWeek = dayCount },
                                label = { Text("$dayCount d") },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Question 3: Daily Study Time
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "3. Daily study time",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "$dailyMinutes min / day",
                            style = MaterialTheme.typography.labelLarge,
                            color = MedicalBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(20, 30, 45, 60, 90).forEach { mins ->
                            FilterChip(
                                selected = dailyMinutes == mins,
                                onClick = { dailyMinutes = mins },
                                label = { Text("${mins}m") },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Question 4: Section Completion Criteria
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Full Study Mode",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = if (requireAll) "Require Read + Questions + Enforce for section completion" else "Reading alone marks section complete",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = requireAll,
                            onCheckedChange = { requireAll = it },
                            modifier = Modifier.testTag("full_study_toggle")
                        )
                    }
                }

                // Live Calculated Pace Card
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MedicalTeal.copy(alpha = 0.08f)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Your Personalized Pace Plan",
                            style = MaterialTheme.typography.labelMedium,
                            color = MedicalTeal,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "$calculatedPagesPerDay",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MedicalBlue
                                )
                                Text("pages / day", style = MaterialTheme.typography.labelSmall)
                            }
                            Column {
                                Text(
                                    text = "$calculatedSectionsPerDay",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text("sections / day", style = MaterialTheme.typography.labelSmall)
                            }
                            Column {
                                Text(
                                    text = "$targetDays",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text("study days", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = null,
                                tint = MedicalTeal,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Estimated Finish: $finishDateStr",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Action Button
                Button(
                    onClick = {
                        onSavePlan(
                            selectedTimeframeOption,
                            targetDays,
                            daysPerWeek,
                            dailyMinutes,
                            requireAll
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("save_study_plan_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Save & Launch Study GPS", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
