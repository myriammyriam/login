package com.example.login.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import android.util.Base64
import com.example.login.R


class Database(var context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TITLE TEXT, " +
                    "$COLUMN_DATE TEXT, " +
                    "$COLUMN_DESCRIPTION TEXT, " +
                    "$COLUMN_IMAGE TEXT);"
        db.execSQL(query)
    }

    //create
    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Add a new anime to the database
    fun addAnime(title: String, date: String, description: String, base64Image: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DATE, date)
        cv.put(COLUMN_DESCRIPTION, description)
        cv.put(COLUMN_IMAGE, base64Image)

        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show()
            Log.d("Database", "Anime added: $title, Date: $date, Description: $description")
        }
    }

    // Get all anime
    /*val animes: List<Anime>
        get() {
            val animeList: MutableList<Anime> = ArrayList()
            val db = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME"
            val cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") val title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                    @SuppressLint("Range") val date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
                    @SuppressLint("Range") val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    @SuppressLint("Range") val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    @SuppressLint("Range") val imageBase64 = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))

                    val imageId = R.drawable.p1
                    animeList.add(Anime(id, title, date, description, imageBase64, imageId))
                } while (cursor.moveToNext())
            }
            cursor.close()
            return animeList
        }
        gfjlf .hfudklfqltroormgfn hfkkhdl=haqs test1
        date1
        desc1
        */
    val animes: List<Anime>
        get() {
            val animeList: MutableList<Anime> = ArrayList()
            val db = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME"
            val cursor = db.rawQuery(query, null)


            if (cursor.moveToFirst()) {

                val titleIndex = cursor.getColumnIndex(COLUMN_TITLE)
                val dateIndex = cursor.getColumnIndex(COLUMN_DATE)
                val descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION)
                val idIndex = cursor.getColumnIndex(COLUMN_ID)
                val imageBase64Index = cursor.getColumnIndex(COLUMN_IMAGE)


                if (titleIndex != -1 && dateIndex != -1 && descriptionIndex != -1 && idIndex != -1 && imageBase64Index != -1) {
                    do {
                        val title = cursor.getString(titleIndex)
                        val date = cursor.getString(dateIndex)
                        val description = cursor.getString(descriptionIndex)
                        val id = cursor.getInt(idIndex)
                        val imageBase64 = cursor.getString(imageBase64Index)

                        val imageId = R.drawable.p1
                        animeList.add(Anime(id, title, date, description, imageBase64, imageId))
                    } while (cursor.moveToNext())
                }
            }

            cursor.close()
            return animeList
        }


    // Delete an anime
    fun deleteAnime(title: String) {
        val db = this.writableDatabase

        val rowsDeleted = db.delete(TABLE_NAME, "$COLUMN_TITLE = ?", arrayOf(title))

        if (rowsDeleted > 0) {
            Toast.makeText(context, "Anime deleted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Failed to delete anime", Toast.LENGTH_SHORT).show()
        }
    }

    // Update an anime
    fun updateAnime(oldTitle: String, newTitle: String, newDate: String, newDescription: String, newBase64Image: String?) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, newTitle)
        cv.put(COLUMN_DATE, newDate)
        cv.put(COLUMN_DESCRIPTION, newDescription)
        cv.put (COLUMN_IMAGE, newBase64Image)

        val rowsAffected = db.update(TABLE_NAME, cv, "$COLUMN_TITLE LIKE ?", arrayOf(oldTitle))
        if (rowsAffected > 0) {
            Toast.makeText(context, "Anime updated successfully", Toast.LENGTH_SHORT).show()
            Log.d("Database", "Anime updated: $oldTitle -> $newTitle")
        } else {
            Toast.makeText(context, "Failed to update anime", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        const val DATABASE_NAME = "Anime.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "Anime_List"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "Anime_title"
        const val COLUMN_DATE = "Anime_date"
        const val COLUMN_DESCRIPTION = "Anime_description"
        const val COLUMN_IMAGE = "Anime_image"
    }
}
