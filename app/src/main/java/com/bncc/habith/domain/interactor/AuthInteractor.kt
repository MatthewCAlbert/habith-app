package com.bncc.habith.domain.interactor

import com.bncc.habith.data.remote.response.UserResponse

interface AuthInteractor {
    suspend fun toLogin(user: String, pass: String): UserResponse?
    suspend fun toRegister(
        name: String,
        email: String,
        password: String,
        username: String
    ): UserResponse?
    suspend fun toDetail(): UserResponse?
}