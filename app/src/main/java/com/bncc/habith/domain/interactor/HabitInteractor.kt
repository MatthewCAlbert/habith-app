package com.bncc.habith.domain.interactor

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse

interface HabitInteractor {
    suspend fun getHabithAll(): BaseResponse<List<HabithResponse>>
    suspend fun getHabithOngoing(): BaseResponse<List<HabithResponse>>
    suspend fun createHabith(data: HabithResponse): BaseResponse<HabithResponse>
    suspend fun removeHabith(id: String): BaseResponse<HabithResponse>
}