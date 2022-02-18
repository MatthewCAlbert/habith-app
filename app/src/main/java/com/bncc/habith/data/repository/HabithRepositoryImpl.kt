package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.network.ApiService
import com.bncc.habith.data.remote.response.HabithResponse
import javax.inject.Inject

class HabithRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HabithRepository {

    override suspend fun getHabithAll(): ArrayList<HabithResponse> {
        val response: ArrayList<HabithResponse> = arrayListOf()
        response.add(HabithResponse(1, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(2, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(3, "Exam", "collage", "final exam 2021"))

        return response
    }

    override suspend fun getHabithOngoing(): ArrayList<HabithResponse> {
        val response: ArrayList<HabithResponse> = arrayListOf()
        response.add(HabithResponse(1, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(2, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(3, "Exam", "collage", "final exam 2021"))

        return response
    }
}