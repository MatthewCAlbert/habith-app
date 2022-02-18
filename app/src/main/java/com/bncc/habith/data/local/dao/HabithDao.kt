package com.bncc.habith.data.local.dao

import androidx.room.*
import com.bncc.habith.data.local.entity.HabithEntity

@Dao
interface HabithDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabith(vararg habith: HabithEntity)

    @Update
    suspend fun updateHabith(vararg habith: HabithEntity)

    @Delete
    suspend fun deleteHabith(vararg habith: HabithEntity)
}