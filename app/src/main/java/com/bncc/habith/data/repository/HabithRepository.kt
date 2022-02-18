package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse

interface HabithRepository {
    suspend fun getHabithAll(): ArrayList<HabithResponse>
    suspend fun getHabithOngoing(): ArrayList<HabithResponse>

    suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse>
}
