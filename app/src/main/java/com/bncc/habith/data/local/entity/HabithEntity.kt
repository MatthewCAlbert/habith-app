package com.bncc.habith.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "habith_table")
data class HabithEntity(
    @PrimaryKey val id:Int? = null,
    val title: String,
    val category: String,
    val description: String,
    val target: Int?,
    val target_type: String,

    val startDate: Date,
    val endDate: Date,
    val repeat_every_day: Int?,
    val repeat_on: String,

    val updated_at: Date,
    val createdAt: Date
    )

data class UserEntity(
    @PrimaryKey val id: Int? = null,
    val username: String,
    val email: String,
    val password: String
)