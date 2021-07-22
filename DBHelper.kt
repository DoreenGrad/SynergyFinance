package com.example.synergyfinance

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
    }

    fun insertData(username: String?, password: String?): Boolean {
        val myDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = myDB.insert("users", null, contentValues)
        return result != -1L
    }

    fun updatePassword(username: String, password: String?): Boolean {
        val myDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("password", password)
        val result = myDB.update("users", contentValues, "username = ?", arrayOf(username)).toLong()
        return result != -1L
    }

    fun checkUsername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("Select * from users where username = ?", arrayOf(username))
        return cursor.count > 0
    }

    fun checkUsernamePassword(username: String, password: String): Boolean {
        val myDB = this.writableDatabase
        val cursor = myDB.rawQuery(
            "Select * from users where username = ? and password = ?",
            arrayOf(username, password)
        )
        return cursor.count > 0
    }

    companion object {
        const val DBNAME = "Login.db"
    }
}