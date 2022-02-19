package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.network.ApiService
import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.util.UserPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HabithRepositoryImpl @Inject constructor(
    private val api: ApiService
) : HabithRepository {

    override suspend fun getHabithAll(token: String): BaseResponse<List<HabithResponse>> {
        return withContext(Dispatchers.IO) {
            api.getAllHabithWithHistory(token)
        }
    }

    override suspend fun getHabithOngoing(token: String): BaseResponse<List<HabithResponse>> {
        return withContext(Dispatchers.IO) {
            api.getAllHabithWithHistory(token)
        }
    }

    override suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.toLogin(username, password)
        }
    }

    override suspend fun toRegister(email: String, username: String, password: String, name: String): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.toRegister(name, email, username, password)
        }
    }
}