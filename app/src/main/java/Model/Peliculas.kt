package Model

import androidx.annotation.DrawableRes

data class Peliculas(
    val id: Int = 0,
    val titulo: String,
    val director: String,
    val anioEstreno: Int,
    val vista: Boolean = false,
    val esFavorita: Boolean = false,
    // Haré esto solo por inventar porque lo vi en un video y me dió curiosidad
    @DrawableRes val imagen: Int? = null
    // No me salió muy bien, pero lo dejo para el futuro
)
