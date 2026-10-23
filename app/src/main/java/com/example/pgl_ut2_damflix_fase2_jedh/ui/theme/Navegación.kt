package com.example.pgl_ut2_damflix_fase2_jedh.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.ui.graphics.vector.ImageVector

    sealed class Navegacion(val path: String, val label: String, val icon: ImageVector) {
        object Form : Navegacion(
            path = "form",
            label = "Form",
            icon = Icons.Filled.AddBox
        )
        object Perfil : Navegacion(
            path = "perfil",
            label = "Perfil",
            icon = Icons.Filled.AccountCircle
        )
        object Lista : Navegacion(
            path = "lista",
            label = "Lista",
            icon = Icons.Filled.DensitySmall
        )
    }

