package Model

import android.content.ContentValues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeliculaRepository(private val dbHelper: DAMFlixDbHelper)  {

    suspend fun obtenerTodas(): List<Peliculas> = withContext(Dispatchers.IO) {
        val lista = mutableListOf<Peliculas>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${DAMFlixDbHelper.TABLE_PELICULAS} ORDER BY ${DAMFlixDbHelper.COLUMN_ID} DESC",
            null
        )

        cursor.use { c ->
            if (c.moveToFirst()) {
                do{
                    val id = c.getInt(c.getColumnIndexOrThrow(DAMFlixDbHelper.COLUMN_ID))
                    val titulo = c.getString(c.getColumnIndexOrThrow(DAMFlixDbHelper.COLUMN_TITLE))
                    val director = c.getString(c.getColumnIndexOrThrow(DAMFlixDbHelper.COLUMN_DIRECTOR))
                    val anio = c.getInt(c.getColumnIndexOrThrow(DAMFlixDbHelper.COLUMN_YEAR))
                    val esVista = c.getInt(c.getColumnIndexOrThrow(DAMFlixDbHelper.COLUMN_WATCHED)) == 1
                    val esFavorita = c.getInt(c.getColumnIndexOrThrow(DAMFlixDbHelper.COLUMN_FAVORITE)) == 1

                    lista.add(Peliculas(id, titulo, director, anio, esVista, esFavorita))
                } while (c.moveToNext())
            }
        }
        lista
    }

    suspend fun insertar(pelicula: Peliculas): Long = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DAMFlixDbHelper.COLUMN_TITLE, pelicula.titulo)
            put(DAMFlixDbHelper.COLUMN_DIRECTOR, pelicula.director)
            put(DAMFlixDbHelper.COLUMN_YEAR, pelicula.anioEstreno)
            put(DAMFlixDbHelper.COLUMN_WATCHED, if (pelicula.vista) 1 else 0)
            put(DAMFlixDbHelper.COLUMN_FAVORITE, if (pelicula.esFavorita) 1 else 0)
        }
        db.insert(DAMFlixDbHelper.TABLE_PELICULAS, null, values)
    }

    suspend fun actualizar(pelicula: Peliculas): Int = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DAMFlixDbHelper.COLUMN_TITLE, pelicula.titulo)
            put(DAMFlixDbHelper.COLUMN_DIRECTOR, pelicula.director)
            put(DAMFlixDbHelper.COLUMN_YEAR, pelicula.anioEstreno)
            put(DAMFlixDbHelper.COLUMN_WATCHED, if (pelicula.vista) 1 else 0)
            put(DAMFlixDbHelper.COLUMN_FAVORITE, if (pelicula.esFavorita) 1 else 0)
        }
        db.update(
            DAMFlixDbHelper.TABLE_PELICULAS,
            values,
            "${DAMFlixDbHelper.COLUMN_ID} = ?",
            arrayOf(pelicula.id.toString())
        )
    }
}
