package com.bncc.habith.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bncc.habith.data.local.dao.HabithDao
import com.bncc.habith.data.local.entity.HabithEntity

@Database(entities = [HabithEntity::class], version = 1)
abstract class HabithDatabase: RoomDatabase() {
    abstract fun habithDao(): HabithDao
}