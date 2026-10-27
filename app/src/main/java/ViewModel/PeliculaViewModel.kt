package ViewModel

import Model.PeliculaRepository
import Model.Peliculas
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class PeliculaViewModel(private val repository: PeliculaRepository) : ViewModel() {

    private val _listaPelicula = MutableStateFlow<List<Peliculas>>(emptyList())
    val listaPelicula: StateFlow<List<Peliculas>> = _listaPelicula.asStateFlow()

    init {
        cargarPeliculas()
    }

    fun cargarPeliculas() {
        viewModelScope.launch {
            _listaPelicula.value = repository.obtenerTodas()
        }
    }

    fun agregarPelicula(title: String, director: String, year: Int, watched: Boolean) {
        viewModelScope.launch {
            val nueva = Peliculas(titulo = title, director = director, anioEstreno = year, vista = watched)
            repository.insertar(nueva)
            cargarPeliculas()
        }
    }

    fun conmutarFavorito(pelicula: Peliculas) {
        viewModelScope.launch {
            val actualizada = pelicula.copy(esFavorita = !pelicula.esFavorita)
            repository.actualizar(actualizada)
            cargarPeliculas()
        }
    }
}