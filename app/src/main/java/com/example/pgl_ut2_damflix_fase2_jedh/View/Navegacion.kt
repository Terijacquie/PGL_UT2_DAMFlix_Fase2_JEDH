package com.example.pgl_ut2_damflix_fase2_jedh.View

import com.example.pgl_ut2_damflix_fase2_jedh.ViewModel.PeliculaViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

sealed class Navegacion(val path: String, val label: String, val icon: ImageVector) {
    object Form : Navegacion(
        path = "form",
        label = "Form",
        icon = Icons.Filled.AddBox
    )
    object Perfil : Navegacion(
        path = "perfil",
        label = "Perfil",
        icon = Icons.Filled.AccountCircle
    )
    object Lista : Navegacion(
        path = "lista",
        label = "Lista",
        icon = Icons.Filled.DensitySmall
    )
}

// Nota: Para que el main se viera más limpio, pase el bottom bar aquí
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDAMFlixMainScreen(viewModel: PeliculaViewModel) {
    val navController = rememberNavController()
    // 1. Estado del Drawer (Abierto / Cerrado)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // 2. CoroutineScope necesario para animar la apertura/cierre
    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val opcionesMenu = listOf(Navegacion.Form, Navegacion.Perfil, Navegacion.Lista)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Cabecera del Menú Lateral
                Box(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Text(
                        text = "Navegación",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Opciones editables del menú
                opcionesMenu.forEach { opcion ->
                    NavigationDrawerItem(
                        icon = { Icon(opcion.icon, contentDescription = opcion.label) },
                        label = { Text(opcion.label) },
                        selected = rutaActual == opcion.path,
                        onClick = {
                            // Cierre asíncrono del panel
                            coroutineScope.launch { drawerState.close() }
                            if (rutaActual != opcion.path) {
                                navController.navigate(opcion.path) {
                                   // popUpTo(navController.graph.startDestinationId) { saveState = true }
                                   // launchSingleTop = true
                                   // restoreState = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        // Contenido Principal envuelto en Scaffold
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Catálogo de Películas") },
                    navigationIcon = {
                        // Botón de hamburguesa para abrir el Drawer
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir Menú")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                )
            },
            bottomBar = {
                NavigationBar {
                    opcionesMenu.forEach { opcion ->
                        NavigationBarItem(
                            selected = rutaActual == opcion.path,
                            onClick = {
                                if (rutaActual != opcion.path) {
                                    navController.navigate(opcion.path) {
                                       // popUpTo(navController.graph.startDestinationId) { saveState = true }
                                       // launchSingleTop = true
                                       // restoreState = true
                                    }
                                }
                            },
                            icon = { Icon(opcion.icon, contentDescription = opcion.label) },
                            label = { Text(opcion.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Navegacion.Form.path,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Navegacion.Form.path) {
                    FormularioPelis(
                        viewModel = viewModel,
                        onPeliculaGuardada = {
                            navController.navigate(Navegacion.Lista.path)
                        }
                    )
                }
                composable(Navegacion.Perfil.path) {
                    CriticosAutores()
                }
                composable(Navegacion.Lista.path) {
                    CatalogoPelis(viewModel = viewModel)
                }
            }
        }
    }
}
