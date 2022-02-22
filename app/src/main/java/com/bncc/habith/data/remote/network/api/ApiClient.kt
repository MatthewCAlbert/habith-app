package com.bncc.habith.data.remote.network.api

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse
import retrofit2.Response
import java.lang.Exception
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

    suspend fun getHabithAll(): BaseResponse<List<HabithResponse.Data>> {
        return safeApiCall { api.getAllHabithWithHistory() }
    }

    suspend fun getHabithOngoing(): BaseResponse<List<HabithResponse.Data>> {
        return safeApiCall { api.getAllHabithWithHistory() }
    }

    suspend fun createHabith(data: HabithResponse): BaseResponse<HabithResponse> {
        return safeApiCall { api.createHabith("", "", "", 0, "", 0) }
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