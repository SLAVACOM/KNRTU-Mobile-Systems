package com.example.knrtu_mobile_systems.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colors inspired by the flag of Tatarstan: green, white and red
private val LightColors = lightColorScheme(
    primary = Color(0xFF1B6B3A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA6F4B4),
    onPrimaryContainer = Color(0xFF00210C),
    secondary = Color(0xFFA63A2F),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDAD5),
    onSecondaryContainer = Color(0xFF410001),
    background = Color(0xFFF7FBF3),
    surface = Color(0xFFF7FBF3),
    surfaceVariant = Color(0xFFDDE5DA),
    onSurfaceVariant = Color(0xFF414941),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8BD79A),
    onPrimary = Color(0xFF003918),
    primaryContainer = Color(0xFF005226),
    onPrimaryContainer = Color(0xFFA6F4B4),
    secondary = Color(0xFFFFB4A9),
    onSecondary = Color(0xFF65130B),
    secondaryContainer = Color(0xFF85251B),
    onSecondaryContainer = Color(0xFFFFDAD5),
    background = Color(0xFF191C19),
    surface = Color(0xFF191C19),
    surfaceVariant = Color(0xFF414941),
    onSurfaceVariant = Color(0xFFC1C9BF),
)

@Composable
fun MyCityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
