package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MedicalTealLight,
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004F58),
    onPrimaryContainer = Color(0xFF97F0FF),
    secondary = MedicalBlueLight,
    onSecondary = Color(0xFF002B73),
    secondaryContainer = Color(0xFF003E9E),
    onSecondaryContainer = Color(0xFFD8E2FF),
    tertiary = MedicalAmberLight,
    onTertiary = Color(0xFF452B00),
    tertiaryContainer = Color(0xFF633F00),
    onTertiaryContainer = Color(0xFFFFDDB4),
    background = SlateBackgroundDark,
    onBackground = Color(0xFFF1F5F9),
    surface = SlateSurfaceDark,
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = SlateSurfaceVariantDark,
    onSurfaceVariant = Color(0xFFCBD5E1),
    error = ErrorRed,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = MedicalTeal,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB0F0F8),
    onPrimaryContainer = Color(0xFF002024),
    secondary = MedicalBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD8E2FF),
    onSecondaryContainer = Color(0xFF001946),
    tertiary = MedicalAmber,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDDB4),
    onTertiaryContainer = Color(0xFF2A1700),
    background = SlateBackgroundLight,
    onBackground = Color(0xFF0F172A),
    surface = SlateSurfaceLight,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = SlateSurfaceVariantLight,
    onSurfaceVariant = Color(0xFF475569),
    error = ErrorRed,
    onError = Color.White
)

@Composable
fun FarrsPhysicsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
