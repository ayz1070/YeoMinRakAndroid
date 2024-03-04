package kr.co.lion.yeominrak

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "User.db",null, 1 ){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table UserTable
            | (idx integer primary key autoincrement,
            | userName text not null,
            | userNickname text not null,
            | userWeek text not null,
            | userProfileImage text not null)
        """.trimMargin()

        val sqlPost = """create table PostTable
            |(idx integer primary key autoincrement,
            |postTitle text not null,
            |postContent text not null,
            |postUserName text not null,
            |postUserNickname text not null,
            |postDate text not null,
            |postWeek text not null)
        """.trimMargin()

        db?.execSQL(sql)
        db?.execSQL(sqlPost)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}