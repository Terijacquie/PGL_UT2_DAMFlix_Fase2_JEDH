package com.example.pgl_ut2_damflix_fase2_jedh.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pgl_ut2_damflix_fase2_jedh.ViewModel.DetallePeliculaViewModel
import com.example.pgl_ut2_damflix_fase2_jedh.ViewModel.DetalleUiState

@Composable
fun PantallaDetalleScreen(
    peliculaId: Int,
    viewModel: DetallePeliculaViewModel,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(peliculaId) {
        viewModel.cargarDetallePelicula(peliculaId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onVolver) {
            Text(text = " <- Volver")
        }
        Spacer(modifier = Modifier.height(16.dp))

        when(val state = uiState){
            is DetalleUiState.Cargando -> {
                Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        // Cargando datos room en segundo plano
                        Text(text = "Cargando...")
                    }
                }
            }
            is DetalleUiState.Exito -> {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = state.pelicula.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Director: ${state.pelicula.director}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Año: ${state.pelicula.year}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = if (state.pelicula.watched) "Vista" else "No vista",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            is DetalleUiState.Error -> {
                Text(
                text = "Error: ${state.mensaje}",
                color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}