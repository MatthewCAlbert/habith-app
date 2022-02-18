package com.bncc.habith.data.remote

import com.bncc.habith.data.remote.response.HabithResponse

class HabithRepository {

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