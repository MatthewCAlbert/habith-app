package com.bncc.habith.data.repository

import com.bncc.habith.data.remote.response.HabithResponse

interface HabithRepository {
    suspend fun getHabithAll(): ArrayList<HabithResponse>
    suspend fun getHabithOngoing(): ArrayList<HabithResponse>
}
