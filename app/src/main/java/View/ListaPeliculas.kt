package View

import Model.Peliculas
import View.Other.PGL_UT2_DAMFlix_Fase2_JEDHTheme
import ViewModel.PeliculaViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoPelis(
    // Lo mismo que en el formulario. Para que pueda verse en el preview
    viewModel: PeliculaViewModel,
    modifier: Modifier = Modifier
) {
    // nuevo añadido 27/10/26

    val peliculas by viewModel.listaPelicula.collectAsStateWithLifecycle()

    // 1. Declaramos la lista mutable interna de la pantalla
    // 27/10/26: Lo dejaré por el momento
    val listaPeliculas = remember {
        mutableStateListOf(
            Peliculas(
                id = 1,
                titulo = "Inception",
                director = "Christopher Nolan",
                anioEstreno = 2010,
                vista = true,
                esFavorita = true
            ),
            Peliculas(
                id = 2,
                titulo = "Interstellar",
                director = "Christopher Nolan",
                anioEstreno = 2014,
                vista = true,
                esFavorita = false
            ),
            Peliculas(
                id = 3,
                titulo = "Spider-Man",
                director = "Joaquim Dos Santos",
                anioEstreno = 2023,
                vista = false,
                esFavorita = true
            )
        )
    }

    // 2. Definimos la función para alternar favorito
    val toggleFavorito: (Int) -> Unit = { id ->
        val indice = listaPeliculas.indexOfFirst { it.id == id }
        if (indice != -1) {
            val peli = listaPeliculas[indice]
            listaPeliculas[indice] = peli.copy(esFavorita = !peli.esFavorita)
        }
    }

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
           // contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = peliculas, key = { p -> p.id }) { pelicula ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = pelicula.titulo, modifier = Modifier.weight(1f))
                        IconButton(onClick = { viewModel.conmutarFavorito(pelicula)}) {
                            Icon(
                                imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (pelicula.esFavorita) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun CatalogoPreview() {
    PGL_UT2_DAMFlix_Fase2_JEDHTheme {
        CatalogoPelis()
    }*/

