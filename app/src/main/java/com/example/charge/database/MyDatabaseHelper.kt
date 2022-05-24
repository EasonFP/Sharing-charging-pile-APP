package com.example.charge.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int)
    : SQLiteOpenHelper(context, name, null, version) {
    private val createUser = "create table User (" +
            "user_id integer primary key autoincrement," +
            "user_nickname text," +
            "user_phone integer," +
            "user_email text," +
            "user_password text," +
            "user_name text," +
            "user_idCard text," +
            "user_carNumber text," +
            "user_VIP integer)"

    private val createOrders = "create table Orders (" +
            "order_id integer primary key autoincrement," +
            "user_id integer," +
            "order_time_begin text," +
            "order_time_end text," +
            "order_time_sum text," +
            "order_place text," +
            "order_price real)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUser)
        db.execSQL(createOrders)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists User")
        db.execSQL("drop table if exists Orders")
        onCreate(db)
    }

}