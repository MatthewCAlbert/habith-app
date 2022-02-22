package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.network.api.ApiClient
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.UserResponse
import javax.inject.Inject

class HabithRepositoryImpl @Inject constructor(
    private val api: ApiClient
) : HabithRepository {
    override suspend fun getHabithAll(): List<HabithResponse.Data>? {
        val request = api.getHabithAll()

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun getHabithOngoing(): List<HabithResponse.Data>? {
        val request = api.getHabithOngoing()

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun createHabith(data: HabithResponse): HabithResponse? {
        val request = api.createHabith(data)

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun removeHabith(id: String): HabithResponse? {
        val request = api.removeHabith(id)

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun toLogin(username: String, password: String): UserResponse? {
        val request = api.toLogin(username, password)

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun toRegister(
        email: String,
        username: String,
        password: String,
        name: String
    ): UserResponse? {
        val request = api.toRegister(email, username, password, name)

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun getUserDetail(): UserResponse? {
        val request = api.getUserDetail()

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun updateUserDetail(name: String): UserResponse? {
        val request = api.updateUserDetail(name)

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

    override suspend fun updateUserPassword(old: String, new: String): UserResponse? {
        val request = api.updateUserPassword(old, new)

        if (!request.isSuccessful) return null

        if (request.failed) return null

        return request.body
    }

}