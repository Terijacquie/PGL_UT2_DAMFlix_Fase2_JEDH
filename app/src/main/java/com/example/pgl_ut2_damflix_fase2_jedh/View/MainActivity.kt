package com.example.pgl_ut2_damflix_fase2_jedh.View

import com.example.pgl_ut2_damflix_fase2_jedh.View.Other.PGL_UT2_DAMFlix_Fase2_JEDHTheme
import com.example.pgl_ut2_damflix_fase2_jedh.ViewModel.PeliculaViewModel
import com.example.pgl_ut2_damflix_fase2_jedh.ViewModel.PeliculaViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pgl_ut2_damflix_fase2_jedh.DAMflixApplication

// Las siguientes anotaciones Son respecto al paso 4 de la fase 6
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 1. Obtenemos el contenedor global de la app
        val app = application as DAMflixApplication
        // 2. Creamos la fábrica pasándole el repositorio Singleton almacenado en Application
        val factory = PeliculaViewModelFactory(app.repository)
        setContent {
            PGL_UT2_DAMFlix_Fase2_JEDHTheme {
               //3. Compose obtiene y mantiene el Viewmodel retenido ante rotaciones mediante la fábrica
                val viewModel: PeliculaViewModel = viewModel(factory = factory)
               //4. Invocamos la pantalla principal pasando el ViewModel
                AppDAMFlixMainScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FormularioPelis(
    viewModel: PeliculaViewModel? = null,
    onPeliculaGuardada: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anioTexto by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }

    val esFormularioValido = titulo.isNotBlank() &&
            director.isNotBlank() &&
            anioTexto.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = titulo,
            singleLine = true,
            maxLines = 1,
            minLines = 1,
            onValueChange = { titulo = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            readOnly = false,
            textStyle = TextStyle(color = Color.Black),
            label = { Text(text = "Título de la película") },
            placeholder = { Text(text = "Título de la película") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Movie, contentDescription = "")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
            },
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = director,
            singleLine = true,
            maxLines = 1,
            minLines = 1,
            onValueChange = { director = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            textStyle = TextStyle(color = Color.Black),
            label = { Text(text = "Director") },
            placeholder = { Text(text = "Director") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
            },
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = anioTexto,
            singleLine = true,
            maxLines = 1,
            minLines = 1,
            onValueChange = { anioTexto = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            textStyle = TextStyle(color = Color.Black),
            label = { Text(text = "Año de estreno") },
            placeholder = { Text(text = "Año") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
            },
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = esVista,
                onCheckedChange = { esVista = it }
            )
            Text(text = "Vista")
        }

        Button(
            onClick = {
                val anio = anioTexto.toIntOrNull() ?: 0
                viewModel?.agregarPelicula(
                    title = titulo,
                    director = director,
                    year = anio,
                    watched = esVista
                )
                titulo = ""
                director = ""
                anioTexto = ""
                esVista = false
                onPeliculaGuardada()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = esFormularioValido
        ) {
            Text(text = "Guardar Película")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PGL_UT2_DAMFlix_Fase2_JEDHTheme {
        FormularioPelis()
    }
}
