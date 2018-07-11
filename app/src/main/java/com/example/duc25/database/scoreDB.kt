package com.example.duc25.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by duc25 on 3/4/2018.
 */
class scoreDB(var context: Context): SQLiteOpenHelper(context, "score.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "CREATE TABLE scoreGame(name text primary key, score int)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertScore(name: String, score: Int?){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put("name", name)
        value.put("score", score)
        db.insert("scoreGame", null, value)
    }

    fun updateScore(name: String, score: Int?){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put("score", score)
        db.update("scoreGame", value, "name=?", arrayOf(name))
    }

    fun readScore(): Int{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT score FROM scoreGame", null)
        cursor.moveToFirst()
        return cursor.getString(0).toInt()
    }
}