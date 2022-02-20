package com.bncc.habith.domain.interactor

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.UserResponse

interface AuthInteractor {
    suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse>
    suspend fun toRegister(email: String, username: String, password: String, name: String): BaseResponse<UserResponse>
}