package com.example.pgl_ut2_damflix_fase2_jedh.Model

import com.example.pgl_ut2_damflix_fase2_jedh.Model.Local.PeliculaDao
import com.example.pgl_ut2_damflix_fase2_jedh.Model.Local.PeliculaEntity
import kotlinx.coroutines.flow.Flow

class PeliculaRepository(private val dao: PeliculaDao)  {

    val todasPeliculas: Flow<List<PeliculaEntity>> = dao.obtenerTodas()

    suspend fun insertar(pelicula: PeliculaEntity) {
        dao.insertar(pelicula)
    }

    suspend fun actualizar(pelicula: PeliculaEntity) {
        dao.actualizar(pelicula)
    }

    // Agregada 06/11/2026
    suspend fun obtenerPorId(id: Int): PeliculaEntity? {
        return dao.obtenerPorId(id)
    }
}
