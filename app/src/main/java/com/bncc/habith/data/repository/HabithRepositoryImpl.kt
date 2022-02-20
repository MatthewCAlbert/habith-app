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

    override suspend fun getHabithAll(): BaseResponse<List<HabithResponse>> {
        return withContext(Dispatchers.IO) {
            api.getAllHabithWithHistory()
        }
    }

    override suspend fun getHabithOngoing(): BaseResponse<List<HabithResponse>> {
        return withContext(Dispatchers.IO) {
            api.getAllHabithWithHistory()
        }
    }

    override suspend fun createHabith(
        data: HabithResponse
    ): BaseResponse<HabithResponse> {
        return withContext(Dispatchers.IO) {
//            if (data.start.isNullOrEmpty() || data.end.isNullOrEmpty()) {
//                api.createHabith(
//                    token, data.title, data.category, data.description,
//                    data.target, data.target_type, data.repeat_every_day ?: "1"
//                )
//            } else {
            api.createHabith("asdads", "adsasd", "adsasd", 1, "none", 5)
//            }
        }
    }

    override suspend fun removeHabith(id: String): BaseResponse<HabithResponse> {
        return withContext(Dispatchers.IO) {
            api.deleteHabith(id)
        }
    }

    override suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.toLogin(username, password)
        }
    }

    override suspend fun toRegister(
        email: String,
        username: String,
        password: String,
        name: String
    ): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.toRegister(name, email, username, password)
        }
    }
}