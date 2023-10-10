package com.example.todo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.entity.Gorevler

@Database(entities = [Gorevler::class], version = 1)
abstract class Veritabani : RoomDatabase(){
    abstract fun getGorevlerDao() : GorevlerDao
}