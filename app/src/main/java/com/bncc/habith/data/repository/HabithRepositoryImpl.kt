package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.network.ApiService
import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.util.UserPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
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

    override suspend fun createHabith(
        token: String,
        data: HabithResponse
    ): BaseResponse<HabithResponse> {
        return withContext(Dispatchers.IO) {
//            if (data.start.isNullOrEmpty() || data.end.isNullOrEmpty()) {
//                api.createHabith(
//                    token, data.title, data.category, data.description,
//                    data.target, data.target_type, data.repeat_every_day ?: "1"
//                )
//            } else {
            api.createHabith(token, "asdads", "adsasd", "adsasd", 1, "none", 5)
//            }
        }
    }

    override suspend fun removeHabith(token: String, id: String): BaseResponse<HabithResponse> {
        return withContext(Dispatchers.IO) {
            api.deleteHabith(token, id)
        }
    }


    /*-------------------------------------- AUTH ------------------------------------------------*/

    override suspend fun toLogin(
        username: String,
        password: String
    ): BaseResponse<UserResponse> {
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

    override suspend fun getUserDetail(token: String): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.getUserDetail(token)
        }
    }

    override suspend fun updateUserDetail(
        token: String,
        name: String
    ): BaseResponse<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.updateUserDetail(token, name)
        }
    }

    override suspend fun updaterUserPassword(
        token: String,
        old: String,
        new: String
    ): Response<BaseResponse<UserResponse>> {
        return withContext(Dispatchers.IO) {
            api.updateUserPassword(token, old, new, new)
        }
    }


}