package com.example.pgl_ut2_damflix_fase2_jedh
import com.example.pgl_ut2_damflix_fase2_jedh.Model.Local.AppDatabase
import com.example.pgl_ut2_damflix_fase2_jedh.Model.PeliculaRepository
import android.app.Application


class DAMflixApplication: Application() {

    // La base de datos solo se crea cuando se acceda a ella por primera vez
    val database by lazy { AppDatabase.getDatabase(this) }

    // El repositorio se crea cuando se necesite, recibiendo el DAO de la DB perezosa
    val repository by lazy { PeliculaRepository(database.peliculaDao()) }
}