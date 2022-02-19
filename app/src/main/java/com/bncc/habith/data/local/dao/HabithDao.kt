package com.bncc.habith.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.bncc.habith.data.local.entity.HabithEntity

@Dao
interface HabithDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabith(vararg habith: HabithEntity)

    @Delete
    suspend fun deleteHabith(habith: HabithEntity)
}