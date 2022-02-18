package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.network.ApiService
import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HabithRepositoryImpl @Inject constructor(
    private val api: ApiService
) : HabithRepository {

    override suspend fun getHabithAll(): ArrayList<HabithResponse> {
        val response: ArrayList<HabithResponse> = arrayListOf()

        return response
    }

    override suspend fun getHabithOngoing(): ArrayList<HabithResponse> {
        val response: ArrayList<HabithResponse> = arrayListOf()

        return response
    }

    override suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.toLogin(username, password)
        }
    }
}