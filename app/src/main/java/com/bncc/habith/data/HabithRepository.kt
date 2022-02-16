package com.bncc.habith.data

import com.bncc.habith.data.remote.network.ApiService
import com.bncc.habith.data.remote.response.HabithResponse
import javax.inject.Inject

class HabithRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getHabithAll(): ArrayList<HabithResponse>{
        val response : ArrayList<HabithResponse> = arrayListOf()
        response.add(HabithResponse(1, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(2, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(3, "Exam", "collage", "final exam 2021"))

        return response
    }

    suspend fun getHabithOngoing(): ArrayList<HabithResponse>{
        val response : ArrayList<HabithResponse> = arrayListOf()
        response.add(HabithResponse(1, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(2, "Exam", "collage", "final exam 2021"))
        response.add(HabithResponse(3, "Exam", "collage", "final exam 2021"))

        return response
    }
}