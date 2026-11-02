package com.example.pgl_ut2_damflix_fase2_jedh.ViewModel

import com.example.pgl_ut2_damflix_fase2_jedh.Model.PeliculaRepository
import com.example.pgl_ut2_damflix_fase2_jedh.Model.Peliculas
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgl_ut2_damflix_fase2_jedh.Model.Local.PeliculaEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class PeliculaViewModel(private val repository: PeliculaRepository) : ViewModel() {

    //Conversión automática del Flow en StateFlow reactivo para COmpose
    val listaPeliculas: StateFlow<List<PeliculaEntity>> = repository.todasPeliculas
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun agregarPelicula(title: String, director: String, year: Int, watched: Boolean) {
        viewModelScope.launch {
            val pelicula = PeliculaEntity(
                title = title,
                director = director,
                year = year,
                watched = watched
            )
            repository.insertar(pelicula)
            //Room notifica el cambio automáticamente sin llamadas a recargar
        }
    }

    fun conmutarFavorito(pelicula: PeliculaEntity) {
        viewModelScope.launch {
            val peliculaActualizada = pelicula.copy(favorite = !pelicula.favorite)
            repository.actualizar(peliculaActualizada)
        }
    }
}

