package com.example.knrtu_mobile_systems.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = RecipesPrimaryDark,
    onPrimary = RecipesOnPrimaryDark,
    primaryContainer = RecipesPrimaryContainerDark,
    secondary = RecipesSecondaryDark,
    tertiary = RecipesTertiaryDark,
    background = RecipesBackgroundDark,
    surface = RecipesSurfaceDark,
)

private val LightColorScheme = lightColorScheme(
    primary = RecipesPrimaryLight,
    onPrimary = RecipesOnPrimaryLight,
    primaryContainer = RecipesPrimaryContainerLight,
    secondary = RecipesSecondaryLight,
    tertiary = RecipesTertiaryLight,
    background = RecipesBackgroundLight,
    surface = RecipesSurfaceLight,
)

@Composable
fun RecipesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled on purpose so the app keeps its own brand palette.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
