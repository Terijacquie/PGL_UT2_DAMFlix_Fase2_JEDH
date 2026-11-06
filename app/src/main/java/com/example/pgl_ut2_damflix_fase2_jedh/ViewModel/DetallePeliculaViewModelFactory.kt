package com.example.pgl_ut2_damflix_fase2_jedh.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pgl_ut2_damflix_fase2_jedh.Model.PeliculaRepository

class DetallePeliculaViewModelFactory(
    private val repository: PeliculaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetallePeliculaViewModel::class.java)) {
            return DetallePeliculaViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel no conocida: ${modelClass.name}")
    }
}