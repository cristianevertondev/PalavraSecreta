package com.cristian.palavrasecreta.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = BrandGreen,
    onPrimary = SurfaceLight,
    secondary = BrandYellow,
    onSecondary = OnBrandDark,
    tertiary = BrandOrange,
    background = BrandCream,
    onBackground = OnBrandDark,
    surface = SurfaceLight,
    onSurface = OnBrandDark
)

/**
 * Tema do jogo. Usamos sempre o esquema de luz com a identidade própria,
 * sem "dynamic color" do sistema, para manter o visual consistente do jogo.
 */
@Composable
fun PalavraSecretaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}