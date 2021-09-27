package com.enty.ilinkcatsandducks.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageItemModel::class], version = 1)
abstract class FavoriteDataBase(): RoomDatabase() {
    abstract fun getDao(): FavoriteDao
}