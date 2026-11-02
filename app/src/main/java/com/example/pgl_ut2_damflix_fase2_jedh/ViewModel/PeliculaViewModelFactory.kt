package com.example.pgl_ut2_damflix_fase2_jedh.ViewModel

import com.example.pgl_ut2_damflix_fase2_jedh.Model.PeliculaRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PeliculaViewModelFactory(
    private val repository: PeliculaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Validamos que se nos pida instanciar nuestro PeliculaViewModel
        if (modelClass.isAssignableFrom(PeliculaViewModel::class.java)) {
            return PeliculaViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel no reconocida: ${modelClass.name}")
    }
}