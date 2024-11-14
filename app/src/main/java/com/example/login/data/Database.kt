package com.example.login.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.login.R

class Database(var context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query =
            "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT); "

        db.execSQL(query)
    }


    //create
    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    //add
    fun addAnime(title: String, date: String, description: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DATE, date)
        cv.put(COLUMN_DESCRIPTION, description)

        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show()
            Log.d("Database", "Anime added: $title, Date: $date, Description: $description")
        }
    }


    val animes: List<Anime>

        get() {
            val animeList: MutableList<Anime> = ArrayList()
            val db = this.readableDatabase
            val query = "SELECT * FROM " + TABLE_NAME
            val cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") val title = cursor.getString(
                        cursor.getColumnIndex(
                            COLUMN_TITLE
                        )
                    )
                    @SuppressLint("Range") val date = cursor.getString(
                        cursor.getColumnIndex(
                            COLUMN_DATE
                        )
                    )
                    @SuppressLint("Range") val description = cursor.getString(
                        cursor.getColumnIndex(
                            COLUMN_DESCRIPTION
                        )
                    )
                    @SuppressLint("Range") val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))


                    val imageId = R.drawable.p1
                    animeList.add(Anime(id, title, date, description, imageId))
                } while (cursor.moveToNext())
            }
            cursor.close()
            return animeList
        }

    //delete
    fun deleteAnime(title: String) {
        val db = this.writableDatabase

        val rowsDeleted = db.delete(TABLE_NAME, COLUMN_TITLE + " = ?", arrayOf(title.toString()))

        if (rowsDeleted > 0) {
            Toast.makeText(context, "Anime deleted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Failed to delete anime", Toast.LENGTH_SHORT).show()
        }
    }

    //update
    fun updateAnime(oldTitle: String, newTitle: String, newDate: String?, newDescription: String?) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, newTitle)
        cv.put(COLUMN_DATE, newDate)
        cv.put(COLUMN_DESCRIPTION, newDescription)

        val rowsAffected = db.update(TABLE_NAME, cv, COLUMN_TITLE + " LIKE ?", arrayOf(oldTitle))
        if (rowsAffected > 0) {
            Toast.makeText(context, "Anime updated successfully", Toast.LENGTH_SHORT).show()
            Log.d("Database", "Anime updated: $oldTitle$newTitle")
        } else {
            Toast.makeText(context, "Failed to update anime", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        const val DATABASE_NAME: String = "Anime.db"
        const val DATABASE_VERSION: Int = 1


        const val TABLE_NAME: String = "Anime_List"
        const val COLUMN_ID: String = "id"
        const val COLUMN_TITLE: String = "Anime_title"
        const val COLUMN_DATE: String = "Anime_date"
        const val COLUMN_DESCRIPTION: String = "Anime_description"
    }
}
