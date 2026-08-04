package com.modify.music.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ModifyPurpleLight,
    onPrimary = Color.Black,
    primaryContainer = ModifyPurpleDark,
    onPrimaryContainer = ModifyPurpleLight,
    
    secondary = ModifyBlueLight,
    onSecondary = Color.Black,
    secondaryContainer = ModifyBlueDark,
    onSecondaryContainer = ModifyBlueLight,
    
    tertiary = ModifyTealLight,
    onTertiary = Color.Black,
    tertiaryContainer = ModifyTeal,
    onTertiaryContainer = ModifyTealLight,
    
    background = ModifyBackgroundDark,
    onBackground = ModifyOnBackgroundDark,
    
    surface = ModifySurfaceDark,
    onSurface = ModifyOnSurfaceDark,
    surfaceVariant = ModifySurfaceVariantDark,
    onSurfaceVariant = ModifyOnSurfaceVariantDark,
    
    error = ModifyError,
    onError = Color.White,
    
    outline = Color(0xFF888888)
)

private val LightColorScheme = lightColorScheme(
    primary = ModifyPurple,
    onPrimary = Color.White,
    primaryContainer = ModifyPurpleLight,
    onPrimaryContainer = ModifyPurpleDark,
    
    secondary = ModifyBlue,
    onSecondary = Color.White,
    secondaryContainer = ModifyBlueLight,
    onSecondaryContainer = ModifyBlueDark,
    
    tertiary = ModifyTeal,
    onTertiary = Color.White,
    tertiaryContainer = ModifyTealLight,
    onTertiaryContainer = ModifyTeal,
    
    background = ModifyBackgroundLight,
    onBackground = ModifyOnBackgroundLight,
    
    surface = ModifySurfaceLight,
    onSurface = ModifyOnSurfaceLight,
    surfaceVariant = ModifySurfaceVariantLight,
    onSurfaceVariant = ModifyOnSurfaceVariantLight,
    
    error = ModifyError,
    onError = Color.White,
    
    outline = Color(0xFF999999)
)

@Composable
fun ModifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    
    val colorScheme = when {
        dynamicColor -> {
            if (darkTheme) {
                try {
                    dynamicDarkColorScheme(context)
                } catch (e: Exception) {
                    DarkColorScheme
                }
            } else {
                try {
                    dynamicLightColorScheme(context)
                } catch (e: Exception) {
                    LightColorScheme
                }
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ModifyTypography,
        content = content
    )
}
