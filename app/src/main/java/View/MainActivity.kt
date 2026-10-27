package View

import Model.DAMFlixDbHelper
import Model.PeliculaRepository
import View.Other.PGL_UT2_DAMFlix_Fase2_JEDHTheme
import ViewModel.PeliculaViewModel
import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PGL_UT2_DAMFlix_Fase2_JEDHTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Catálogo de películas") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    },
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val rutaActual = navBackStackEntry?.destination?.route

                        NavigationBar {
                            listOf(
                                Navegacion.Form,
                                Navegacion.Perfil,
                                Navegacion.Lista
                            ).forEach { ruta ->
                                NavigationBarItem(
                                    selected = rutaActual == ruta.path,
                                    onClick = {
                                        if (rutaActual != ruta.path) {
                                            navController.navigate(ruta.path) {
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = { Icon(ruta.icon, contentDescription = ruta.label) },
                                    label = { Text(ruta.label) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    val dbHelper = DAMFlixDbHelper(this)

                    val repositorio = PeliculaRepository(dbHelper)

                    val viewModelPeliculas = PeliculaViewModel(repositorio)

                    NavHost(
                        navController = navController,
                        startDestination = Navegacion.Form.path,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Navegacion.Form.path) {
                            FormularioPelis(viewModelPeliculas)
                        }
                        composable(Navegacion.Perfil.path) {
                            CriticosAutores()
                        }
                        composable(Navegacion.Lista.path) {
                            CatalogoPelis(viewModelPeliculas)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FormularioPelis(
    //Le pongo el nulo para que pueda ser opcional y verse en el preview
    viewModel: PeliculaViewModel? = null,
    onPeliculaGuardada: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var titulo by remember {mutableStateOf("")}
    var director by remember {mutableStateOf("")}
    var anioTexto by remember {mutableStateOf("")}
    var esVista by remember {mutableStateOf(false)}

   val esFormularioValido = titulo.isNotBlank() &&
                            director.isNotBlank() &&
                            anioTexto.isNotBlank() != null

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                // Pendiente. El IDE me pidio ponerle el null, debo averiguar por qué
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

