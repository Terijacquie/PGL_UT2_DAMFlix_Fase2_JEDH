package com.example.pgl_ut2_damflix_fase2_jedh.Model.Local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/*Notas de aprendizaje: Data Acces Object (DAO) es uns interfaz donde declaramos
* las operaciones de lectura y escritura de nuestra base de datos. No escribimos
* el código de implementación: anotamos las funciones e interfaces, y el procesador
* KSP genera automáticamente en compilación la clase concreta*/

@Dao
interface PeliculaDao {
    // Al retornar Flow, Room modifica automáticamente cualquier cambio en la tabla.
    @Query("SELECT * FROM peliculas ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<PeliculaEntity>>

    // Agregada 06/11/2026
    @Query("SELECT * FROM peliculas WHERE id = :id")
    suspend fun obtenerPorId(id: Int): PeliculaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(pelicula: PeliculaEntity)

    @Update
    suspend fun actualizar(pelicula: PeliculaEntity)

    @Delete
    suspend fun eliminar(pelicula: PeliculaEntity)
}

/* Notas de aparendizaje 2:
* fun obtenerTodas(): Flow<List<PeliculaEntity>>: La magia de la reactividad.
* Al devolver un Flow, Room observa la tabla SQLite en segundo plano. Cada vez que se añade, modifica o
* elimina un registro, Room emite de forma automática la nueva lista actualizada de películas
* hacia el ViewModel y la interfaz de usuario de Compose.
suspend fun: Todas las operaciones de escritura (@Insert, @Update, @Delete) son
* funciones de suspensión de Corrutinas de Kotlin. Esto garantiza que las operaciones I/O en
*  disco se ejecuten en hilos secundarios fuera del hilo principal de la UI (Main Thread),
* evitando bloqueos o congelamientos de la pantalla.
onConflict = OnConflictStrategy.REPLACE: Estrategia que resuelve automáticamente
* colisiones de clave primaria sobrescribiendo el registro existente si se reinserta.*/