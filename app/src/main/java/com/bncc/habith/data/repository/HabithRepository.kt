package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse
import retrofit2.Response

interface HabithRepository {
    suspend fun getHabithAll(token: String): BaseResponse<List<HabithResponse>>
    suspend fun getHabithOngoing(token: String): BaseResponse<List<HabithResponse>>
    suspend fun createHabith(token: String, data: HabithResponse): BaseResponse<HabithResponse>
    suspend fun removeHabith(token: String, id: String): BaseResponse<HabithResponse>

    suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse>
    suspend fun toRegister(email: String, username: String, password: String, name: String): BaseResponse<UserResponse>
    suspend fun getUserDetail(token: String): BaseResponse<UserResponse>
    suspend fun updateUserDetail(token: String, name: String): BaseResponse<UserResponse>
    suspend fun updaterUserPassword(token: String, old: String, new: String): Response<BaseResponse<UserResponse>>
}
