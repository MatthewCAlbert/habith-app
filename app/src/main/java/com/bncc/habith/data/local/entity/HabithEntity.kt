package com.bncc.habith.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habith_table")
data class HabithEntity(
    @PrimaryKey val id:Int? = null,
    val title: String
)