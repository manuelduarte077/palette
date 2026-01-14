package dev.donmanuel.app.palette.viewmodels

import androidx.lifecycle.ViewModel
import dev.donmanuel.app.palette.models.ColorModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class ColorViewModel : ViewModel() {

    private val _colors = MutableStateFlow<List<ColorModel>>(emptyList())
    val colors: StateFlow<List<ColorModel>> = _colors

    private var id = 1

    fun generateColor() {
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)

        val hex = ColorModel.rgbToHex(r, g, b)
        val rgb = "RGB($r, $g, $b)"

        val newColor = ColorModel(
            id = id++,
            red = r,
            green = g,
            blue = b,
            hex = hex,
            rgb = rgb
        )

        _colors.value += newColor
    }

    fun removeColorById () {}

    fun editColorById () {}

    fun copyColorById () {}

    fun shareColorById () {}

}