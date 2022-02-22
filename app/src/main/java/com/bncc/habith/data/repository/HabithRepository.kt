package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse

interface HabithRepository {
    suspend fun getHabithAll(): HabithResponse?
    suspend fun getHabithOngoing(): HabithResponse?
    suspend fun createHabith(data: HabithResponse): HabithResponse?
    suspend fun removeHabith(id: String): HabithResponse?

    suspend fun toLogin(username: String, password: String): UserResponse?
    suspend fun toRegister(email: String, username: String, password: String, name: String): UserResponse?
    suspend fun getUserDetail(): UserResponse?
    suspend fun updateUserDetail(name: String): UserResponse?
    suspend fun updateUserPassword(old: String, new: String): UserResponse?
}
