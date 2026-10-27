package Model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DAMFlixDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "damflix_native.db"
        private const val DATABASE_VERSION = 1

        // Nombres de tabla y columnas
        const val TABLE_PELICULAS = "peliculas"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DIRECTOR = "director"
        const val COLUMN_YEAR = "year"
        const val COLUMN_WATCHED = "watched"
        const val COLUMN_FAVORITE = "favorite"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSql = """
            CREATE TABLE $TABLE_PELICULAS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT NOT NULL,
                $COLUMN_DIRECTOR TEXT NOT NULL,
                $COLUMN_YEAR INTEGER NOT NULL,
                $COLUMN_WATCHED INTEGER NOT NULL DEFAULT 0,
                $COLUMN_FAVORITE INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent()

        db.execSQL(createTableSql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PELICULAS")
        onCreate(db)
    }
}
