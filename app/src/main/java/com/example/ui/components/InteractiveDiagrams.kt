package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.PaceAcquireColor
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningAmber
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun InteractiveDiagramSelector(
    diagramType: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "PHYSICS SIMULATOR",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = PaceAcquireColor,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Interactive",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            when (diagramType.uppercase()) {
                "XRAY_SPECTRUM" -> XraySpectrumSimulator()
                "ATTENUATION_CURVE" -> AttenuationHvlSimulator()
                "COMPTON_VS_PE", "GRID_CUTOFF" -> AnodeHeelAndScatterSimulator()
                "CT_WINDOWING" -> CtWindowingSimulator()
                "US_TGC", "US_DOPPLER" -> UltrasoundDopplerSimulator()
                "MRI_T1_T2" -> MriRelaxationSimulator()
                else -> AttenuationHvlSimulator()
            }
        }
    }
}

@Composable
fun XraySpectrumSimulator() {
    var kvp by remember { mutableFloatStateOf(80f) }
    var filterMm by remember { mutableFloatStateOf(2.5f) }

    Column {
        Text(
            text = "X-ray Tube Emission Spectrum",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Bremsstrahlung continuous distribution + Tungsten characteristic peaks (Ka @ 58 keV, Kb @ 68 keV)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        val graphColor = MaterialTheme.colorScheme.primary
        val charColor = MedicalAmber

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
                .padding(12.dp)
        ) {
            val w = size.width
            val h = size.height
            val maxE = 150f

            // Baseline axis
            drawLine(
                color = Color.Gray.copy(alpha = 0.5f),
                start = Offset(0f, h),
                end = Offset(w, h),
                strokeWidth = 2f
            )

            val path = Path()
            val points = 60
            val maxKvp = kvp.coerceAtLeast(20f)
            val cutOffFraction = (filterMm * 8f) / 100f

            path.moveTo(0f, h)
            for (i in 0..points) {
                val energy = (i.toFloat() / points) * maxKvp
                val x = (energy / maxE) * w
                if (energy < cutOffFraction * maxKvp) {
                    path.lineTo(x, h)
                } else {
                    // Bremsstrahlung curve: ~ energy * (kVp - energy) with filtration attenuation
                    val raw = (energy * (maxKvp - energy)) / (maxKvp * maxKvp / 4f)
                    val filterFactor = 1f - exp(-0.06f * energy * filterMm).coerceAtMost(0.9f)
                    val yNorm = (raw * filterFactor).coerceIn(0f, 1f)
                    val y = h - (yNorm * (h * 0.8f))
                    path.lineTo(x, y)
                }
            }
            path.lineTo((maxKvp / maxE) * w, h)

            drawPath(
                path = path,
                color = graphColor.copy(alpha = 0.35f)
            )
            drawPath(
                path = path,
                color = graphColor,
                style = Stroke(width = 3f)
            )

            // Draw Characteristic Spikes if kVp >= 70 (Tungsten K-shell binding energy)
            if (maxKvp >= 70f) {
                val xKa = (58f / maxE) * w
                val xKb = (68f / maxE) * w
                drawLine(
                    color = charColor,
                    start = Offset(xKa, h),
                    end = Offset(xKa, h * 0.15f),
                    strokeWidth = 4f
                )
                drawLine(
                    color = charColor,
                    start = Offset(xKb, h),
                    end = Offset(xKb, h * 0.3f),
                    strokeWidth = 3f
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tube Potential: ${kvp.roundToInt()} kVp", style = MaterialTheme.typography.labelMedium)
            Text(
                text = if (kvp >= 70f) "K-characteristic emitted" else "No K-characteristic (< 70 kVp)",
                style = MaterialTheme.typography.labelMedium,
                color = if (kvp >= 70f) SuccessGreen else WarningAmber
            )
        }
        Slider(
            value = kvp,
            onValueChange = { kvp = it },
            valueRange = 40f..140f
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total Filtration: ${String.format("%.1f", filterMm)} mm Al", style = MaterialTheme.typography.labelMedium)
            Text(text = "Hardens beam (absorbs low-E rays)", style = MaterialTheme.typography.labelSmall)
        }
        Slider(
            value = filterMm,
            onValueChange = { filterMm = it },
            valueRange = 0.5f..5.0f
        )
    }
}

@Composable
fun AttenuationHvlSimulator() {
    var thicknessMm by remember { mutableFloatStateOf(60f) }
    var hvlMm by remember { mutableFloatStateOf(30f) }

    val hvlCount = thicknessMm / hvlMm
    val transmittedFraction = 0.5.pow(hvlCount.toDouble()).toFloat()
    val transmittedPercent = (transmittedFraction * 100f).coerceIn(0f, 100f)

    Column {
        Text(
            text = "Exponential Attenuation & Half-Value Layer (HVL)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Formula: I = I₀ × (0.5)^(d / HVL) — Each HVL reduces remaining beam by 50%",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Visual beam attenuation representation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Incident", style = MaterialTheme.typography.labelSmall)
                Text("100%", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MedicalBlue)
            }

            // Attenuator Block
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
                    .height(44.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${thicknessMm.roundToInt()} mm Absorber\n(${String.format("%.2f", hvlCount)} HVLs)",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Transmitted", style = MaterialTheme.typography.labelSmall)
                Text(
                    text = "${String.format("%.1f", transmittedPercent)}%",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (transmittedPercent > 20f) SuccessGreen else MedicalAmber
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Absorber Thickness (d): ${thicknessMm.roundToInt()} mm", style = MaterialTheme.typography.labelMedium)
        Slider(
            value = thicknessMm,
            onValueChange = { thicknessMm = it },
            valueRange = 0f..150f
        )

        Text(text = "Half-Value Layer (HVL): ${hvlMm.roundToInt()} mm", style = MaterialTheme.typography.labelMedium)
        Slider(
            value = hvlMm,
            onValueChange = { hvlMm = it },
            valueRange = 10f..60f
        )
    }
}

@Composable
fun AnodeHeelAndScatterSimulator() {
    var anodeAngle by remember { mutableFloatStateOf(15f) }

    Column {
        Text(
            text = "Anode Heel Effect & Beam Non-Uniformity",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "X-rays emitted deeper in target are attenuated more towards the anode side. Intensity is higher at cathode side.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
                .padding(12.dp)
        ) {
            val w = size.width
            val h = size.height

            // Cathode side (Left, 105%), Anode side (Right, 75%)
            val cathodeIntensity = 1.05f + ((20f - anodeAngle) * 0.015f)
            val anodeIntensity = 0.75f - ((20f - anodeAngle) * 0.02f)

            // Draw gradient intensity bar
            val path = Path()
            path.moveTo(0f, h - (cathodeIntensity * (h * 0.6f)))
            path.lineTo(w, h - (anodeIntensity * (h * 0.6f)))
            path.lineTo(w, h)
            path.lineTo(0f, h)
            path.close()

            drawPath(path, color = PaceAcquireColor.copy(alpha = 0.4f))
            drawLine(
                color = PaceAcquireColor,
                start = Offset(0f, h - (cathodeIntensity * (h * 0.6f))),
                end = Offset(w, h - (anodeIntensity * (h * 0.6f))),
                strokeWidth = 4f
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("CATHODE (Thicker Anatomy)", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = MedicalBlue)
            Text("ANODE (Thinner Anatomy)", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = MedicalAmber)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Target Angle: ${anodeAngle.roundToInt()}° (Smaller angle = worse heel effect)", style = MaterialTheme.typography.labelMedium)
        Slider(
            value = anodeAngle,
            onValueChange = { anodeAngle = it },
            valueRange = 7f..22f
        )
    }
}

@Composable
fun CtWindowingSimulator() {
    var windowWidth by remember { mutableFloatStateOf(400f) }
    var windowLevel by remember { mutableFloatStateOf(40f) }

    val lowerBound = windowLevel - (windowWidth / 2f)
    val upperBound = windowLevel + (windowWidth / 2f)

    Column {
        Text(
            text = "CT Hounsfield Scale & Windowing (W / L)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Window Width (W) controls contrast; Window Level (L) controls central brightness.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Display range: -1000 (Air) to +1000 (Dense Bone)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
                .padding(12.dp)
        ) {
            val w = size.width
            val h = size.height

            // Full HU spectrum background: -1000 to +1000
            val huMin = -1000f
            val huMax = 1000f
            val range = huMax - huMin

            // Draw base bar
            drawRect(
                color = Color.LightGray.copy(alpha = 0.4f),
                topLeft = Offset(0f, h * 0.3f),
                size = Size(w, h * 0.4f)
            )

            // Draw active window range
            val winLeftX = (((lowerBound - huMin) / range) * w).coerceIn(0f, w)
            val winRightX = (((upperBound - huMin) / range) * w).coerceIn(0f, w)
            drawRect(
                color = MedicalTeal.copy(alpha = 0.6f),
                topLeft = Offset(winLeftX, h * 0.3f),
                size = Size((winRightX - winLeftX).coerceAtLeast(4f), h * 0.4f)
            )

            // Markers: Air (-1000), Fat (-100), Water (0), Soft Tissue (+40), Bone (+1000)
            val xWater = (((0f - huMin) / range) * w)
            drawLine(Color.Blue, Offset(xWater, h * 0.15f), Offset(xWater, h * 0.85f), 3f)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Air (-1000)", fontSize = 10.sp, color = Color.Gray)
            Text("Water (0)", fontSize = 10.sp, color = Color.Blue)
            Text("Bone (+1000)", fontSize = 10.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Active Window: [${lowerBound.roundToInt()} HU to ${upperBound.roundToInt()} HU]",
            fontWeight = FontWeight.SemiBold,
            color = MedicalTeal,
            fontSize = 12.sp
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Window Width (W): ${windowWidth.roundToInt()} HU", style = MaterialTheme.typography.labelSmall)
            Text(text = "Window Level (L): ${windowLevel.roundToInt()} HU", style = MaterialTheme.typography.labelSmall)
        }

        Slider(
            value = windowWidth,
            onValueChange = { windowWidth = it },
            valueRange = 50f..1500f
        )
        Slider(
            value = windowLevel,
            onValueChange = { windowLevel = it },
            valueRange = -400f..500f
        )
    }
}

@Composable
fun UltrasoundDopplerSimulator() {
    var angleDeg by remember { mutableFloatStateOf(45f) }

    val angleRad = Math.toRadians(angleDeg.toDouble())
    val cosTheta = cos(angleRad)
    val isErrorHigh = angleDeg > 60f

    Column {
        Text(
            text = "Ultrasound Doppler Angle θ & Cosine Effect",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Doppler Shift: Δf = (2 · f₀ · v · cos θ) / c. For accurate velocity, keep θ between 30° and 60°.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Angle θ = ${angleDeg.roundToInt()}°", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = "cos(θ) = ${String.format("%.3f", cosTheta)}", style = MaterialTheme.typography.bodyMedium)
            }

            Box(
                modifier = Modifier
                    .background(
                        if (isErrorHigh) WarningAmber.copy(alpha = 0.2f) else SuccessGreen.copy(alpha = 0.2f),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = if (isErrorHigh) "CAUTION: θ > 60°\nVelocity error rises rapidly!" else "CLINICALLY OPTIMAL\n(30° ≤ θ ≤ 60°)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isErrorHigh) WarningAmber else SuccessGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = angleDeg,
            onValueChange = { angleDeg = it },
            valueRange = 0f..90f
        )
    }
}

@Composable
fun MriRelaxationSimulator() {
    var tr by remember { mutableFloatStateOf(500f) }
    var te by remember { mutableFloatStateOf(20f) }

    val weighting = when {
        tr < 800f && te < 30f -> "T1-Weighted (Short TR, Short TE)"
        tr > 1800f && te > 70f -> "T2-Weighted (Long TR, Long TE)"
        tr > 1800f && te < 30f -> "Proton Density (PD) (Long TR, Short TE)"
        else -> "Intermediate / Mixed Weighting"
    }

    Column {
        Text(
            text = "MRI Spin-Echo Weighting Simulator",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "TR controls T1 recovery time; TE controls T2 dephasing decay time.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MedicalBlue.copy(alpha = 0.1f), RoundedCornerShape(10.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Contrast: $weighting",
                fontWeight = FontWeight.Bold,
                color = MedicalBlue,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Repetition Time (TR): ${tr.roundToInt()} ms", style = MaterialTheme.typography.labelSmall)
            Text(text = "Echo Time (TE): ${te.roundToInt()} ms", style = MaterialTheme.typography.labelSmall)
        }

        Slider(
            value = tr,
            onValueChange = { tr = it },
            valueRange = 250f..3000f
        )
        Slider(
            value = te,
            onValueChange = { te = it },
            valueRange = 10f..140f
        )
    }
}
