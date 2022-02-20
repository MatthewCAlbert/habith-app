package com.bncc.habith.domain.interactor

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepository
import javax.inject.Inject

class AuthInteractorImpl @Inject constructor(
    private val repository: HabithRepository
) : AuthInteractor {
    override suspend fun toLogin(username: String, password: String): BaseResponse<UserResponse> {
        return repository.toLogin(username, password)
    }

    override suspend fun toRegister(
        email: String,
        username: String,
        password: String,
        name: String
    ): BaseResponse<UserResponse> {
        return repository.toRegister(email, username, password, name)
    }

}