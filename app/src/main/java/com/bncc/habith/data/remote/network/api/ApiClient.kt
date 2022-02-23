package com.bncc.habith.data.remote.network.api

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.HabithResponse2
import com.bncc.habith.data.remote.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val api: ApiService
) {

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): BaseResponse<T> {
        return try {
            BaseResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            BaseResponse.failure(e)
        }
    }

    suspend fun getHabithAll(): BaseResponse<HabithResponse> {
        return safeApiCall { api.getAllHabithWithHistory() }
    }

    suspend fun getHabithOngoing(): BaseResponse<HabithResponse> {
        return safeApiCall { api.getAllHabithWithHistory() }
    }

    suspend fun createHabith(data: HabithResponse2.Data): BaseResponse<HabithResponse2> {
        return safeApiCall {
            if (data.start == null && data.end == null) {
                api.createHabith(
                    data.title,
                    data.category,
                    data.description,
                    data.target,
                    data.target_type!!,
                    data.repeat_every_day.toInt()
                )
            } else {
                api.createHabith(
                    data.title,
                    data.category,
                    data.description,
                    data.target,
                    data.target_type!!,
                    data.start!!,
                    data.end!!,
                    data.repeat_every_day.toInt()
                )
            }
        }
    }

    suspend fun removeHabith(id: String): BaseResponse<HabithResponse> {
        return safeApiCall { api.deleteHabith(id) }
    }

    suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse> {
        return safeApiCall { api.toLogin(username, password) }
    }

    suspend fun toRegister(
        email: String,
        username: String,
        password: String,
        name: String
    ): BaseResponse<UserResponse> {
        return safeApiCall { api.toRegister(name, email, username, password) }
    }

    suspend fun getUserDetail(): BaseResponse<UserResponse> {
        return safeApiCall { api.getUserDetail() }
    }

    suspend fun updateUserDetail(name: String): BaseResponse<UserResponse> {
        return safeApiCall { api.updateUserDetail(name) }
    }

    suspend fun updateUserPassword(old: String, new: String): BaseResponse<UserResponse> {
        return safeApiCall { api.updateUserPassword(old, new, new) }
    }

}