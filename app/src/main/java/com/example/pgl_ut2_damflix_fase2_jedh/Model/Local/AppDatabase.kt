package com.example.pgl_ut2_damflix_fase2_jedh.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* Registra el array de entidades perteneciente a la base de datos, fija la versión del esquema
* para migraciones futuras y deshabilita la exportación del esquema en formato JSON*/
@Database(entities = [PeliculaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    /* Método abstracto que expone el DAO a las capas superiores. La clase generada pos KSP
    * proporcionará la implementación de este método*/
    abstract fun peliculaDao(): PeliculaDao

    companion object {
        /* VOLATILE: Anotación clave Multihilo. Garantiza que cualquier cambio realizado
        * en la variable INSTANCE sea visible inmediatamente para todos los hilos de ejecución
        * del CPU, evitando las lecturas obsoletas. */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            /*Synchronized: bloque de exclusión multihilos. Asegura que si dos fragmentos de código o hilos intentan
            * inicializar la base de datos simultáneamente, solo uno creará la base de datos, previniendo
            * la creación de múltiples instancias en memoria (evita fugas de memoria y bloqueos en SQLite).*/
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    /*Previene fuga de memoria(Memory Leaks) al vincular la vida útil de la base de datos
                    * con toda la app en lugar de con una Activity efímera */
                    context.applicationContext,
                    AppDatabase::class.java,
                    "damflix_room.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}