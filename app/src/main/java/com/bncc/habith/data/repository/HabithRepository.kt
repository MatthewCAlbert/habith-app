package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse

interface HabithRepository {
    suspend fun getHabithAll(token: String): BaseResponse<List<HabithResponse>>
    suspend fun getHabithOngoing(token: String): BaseResponse<List<HabithResponse>>
    suspend fun createHabith(token: String, data: HabithResponse): BaseResponse<HabithResponse>
    suspend fun removeHabith(token: String, id: String): BaseResponse<HabithResponse>

    suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse>
    suspend fun toRegister(email: String, username: String, password: String, name: String): BaseResponse<UserResponse>
}
