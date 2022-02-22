package com.bncc.habith.domain.interactor

import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepository
import javax.inject.Inject

class AuthInteractorImpl @Inject constructor(
    private val repo: HabithRepository
): AuthInteractor {
    override suspend fun toLogin(user: String, pass: String): UserResponse? {
        return repo.toLogin(user, pass)
    }

    override suspend fun toRegister(
        name: String,
        email: String,
        password: String,
        username: String
    ): UserResponse? {
        return repo.toRegister(email, username, password, name)
    }

    override suspend fun toDetail(): UserResponse? {
        return repo.getUserDetail()
    }
}