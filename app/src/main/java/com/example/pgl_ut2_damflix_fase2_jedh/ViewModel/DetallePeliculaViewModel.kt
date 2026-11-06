package com.example.pgl_ut2_damflix_fase2_jedh.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgl_ut2_damflix_fase2_jedh.Model.PeliculaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetallePeliculaViewModel(
    private val repository: PeliculaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetalleUiState>(DetalleUiState.Cargando)
    val uiState: StateFlow<DetalleUiState> = _uiState.asStateFlow()

    fun cargarDetallePelicula(peliculaId: Int) {
        viewModelScope.launch {
            _uiState.value = DetalleUiState.Cargando

            val resultado = withContext(Dispatchers.IO) {
                kotlinx.coroutines.delay(1000) //Nota del profesor: Simulación de retardo para apreciar el spinner de carga
                repository.obtenerPorId(peliculaId)
            }

            if (resultado != null) {
                _uiState.value = DetalleUiState.Exito(resultado)
            } else {
                _uiState.value = DetalleUiState.Error("Peli no encontrada")
            }
        }
    }
}