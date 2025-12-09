package dev.donmanuel.app.palette

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.donmanuel.app.palette.views.HomeView

@Composable
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color.LightGray
        ),
    ) {
        HomeView()
    }
}