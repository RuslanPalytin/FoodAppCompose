package com.example.foodapp.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.foodapp.data.FoodModelDb

class DbHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "FoodDataBase"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "myDataBase"

        private const val ID = "id"
        private const val FOOD_ICON = "icon"
        private const val FOOD_NAME = "name"
        private const val FOOD_PRICE = "price"
        private const val FOOD_COUNT = "count"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOOD_ICON + " TEXT,"
                + FOOD_NAME + " TEXT,"
                + FOOD_PRICE + " TEXT,"
                + FOOD_COUNT + " INTEGER)")
        db.execSQL(query)
    }

    fun addNewFood (item: FoodModelDb) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(FOOD_ICON, item.icon)
        values.put(FOOD_NAME, item.name)
        values.put(FOOD_PRICE, item.price)
        values.put(FOOD_COUNT, item.count)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun readFoods(): MutableList<FoodModelDb>? {
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val foodModel: MutableList<FoodModelDb> = mutableListOf()

        if (cursor.moveToFirst()) {
            do {
                foodModel.add(
                    FoodModelDb(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4).toInt()
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return foodModel
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}