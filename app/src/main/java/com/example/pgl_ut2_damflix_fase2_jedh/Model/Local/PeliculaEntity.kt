package com.example.pgl_ut2_damflix_fase2_jedh.Model.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

/*Notifica a Room que esta data class debe convertirse en una tabla relacional
* llamada películas. Si se omite el tableName, la tabla tomaría el nombre de la clase por defecto*/
@Entity(tableName = "peliculas")
data class PeliculaEntity(
    /*Define el campo id como clave primaria única de la tabla y delega a SQLite el
    * incremento automático. Inicializamos en 0 para que Room lo haga automáticamente*/
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val director: String,
    val year: Int,
    val watched: Boolean = false,
    val favorite: Boolean = false
)

/* Notas: Al usar data class con val, garantizamos la inmutabilidad de los datos
* de las películas en la memoria. Room mapea automáticamente los tipos de datos de Kotlin
* a tipos nativos de SQLite*/

