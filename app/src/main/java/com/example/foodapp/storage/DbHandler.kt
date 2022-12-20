package com.example.foodapp.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.foodapp.data.FoodModelDb

class DbHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "DataBase.db"
        private const val DB_VERSION = 2
        private const val TABLE_NAME = "myDataBase"

        private const val ID = "id"
        private const val FOOD_ID = "food_id"
        private const val FOOD_ICON = "icon"
        private const val FOOD_NAME = "name"
        private const val FOOD_PRICE = "price"
        private const val FOOD_COUNT = "count"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOOD_ID + " TEXT,"
                + FOOD_ICON + " TEXT,"
                + FOOD_NAME + " TEXT,"
                + FOOD_PRICE + " TEXT,"
                + FOOD_COUNT + " INTEGER)")
        db.execSQL(query)
    }

    fun addNewFood (item: FoodModelDb) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(FOOD_ID, item.id)
        values.put(FOOD_ICON, item.icon)
        values.put(FOOD_NAME, item.name)
        values.put(FOOD_PRICE, item.price)
        values.put(FOOD_COUNT, item.count)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun addAllNewFood(items: MutableList<FoodModelDb>) {
        val db = this.writableDatabase

        for(i in 0 until items.size){

            val values = ContentValues()

            values.put(FOOD_ID, items[i].id)
            values.put(FOOD_ICON, items[i].icon)
            values.put(FOOD_NAME, items[i].name)
            values.put(FOOD_PRICE, items[i].price)
            values.put(FOOD_COUNT, items[i].count)

            db.insert(TABLE_NAME, null, values)
        }

        db.close()
    }

    fun readFoods(): MutableList<FoodModelDb> {
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val foodModel: MutableList<FoodModelDb> = mutableListOf()

        if (cursor.moveToFirst()) {
            do {
                foodModel.add(
                    FoodModelDb(
                        id = cursor.getString(1),
                        icon = cursor.getString(2),
                        name = cursor.getString(3),
                        price = cursor.getString(4),
                        count = cursor.getInt(5)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return foodModel
    }

    fun deleteFood(foodId: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$FOOD_ID=?", arrayOf(foodId))
        db.close()
    }

    fun deleteAllFoods(items: MutableList<FoodModelDb>) {
        val db = this.writableDatabase
        for(i in 0 until items.size){
            db.delete(TABLE_NAME, "$FOOD_ID=?", arrayOf(items[i].id))
        }
    }

    fun updateFood(newCount: MutableState<Int>, foodModel: FoodModelDb) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(FOOD_ID, foodModel.id)
        values.put(FOOD_ICON, foodModel.icon)
        values.put(FOOD_NAME, foodModel.name)
        values.put(FOOD_PRICE, foodModel.price)
        values.put(FOOD_COUNT, foodModel.count)

        db.update(TABLE_NAME, values, "count=?", arrayOf(newCount.value.toString()))

        Log.d("MyLog", values.toString())

        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}