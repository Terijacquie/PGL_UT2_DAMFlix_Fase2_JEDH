package com.example.pgl_ut2_damflix_fase2_jedh.ViewModel

import com.example.pgl_ut2_damflix_fase2_jedh.Model.Local.PeliculaEntity

sealed interface DetalleUiState {
    object Cargando : DetalleUiState
    data class Exito(val pelicula: PeliculaEntity) : DetalleUiState
    data class Error(val mensaje: String): DetalleUiState
}