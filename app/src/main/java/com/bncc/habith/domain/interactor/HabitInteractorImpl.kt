package com.bncc.habith.domain.interactor

import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.repository.HabithRepository

class HabitInteractorImpl(
    private val repository: HabithRepository
) : HabitInteractor {
    override suspend fun getHabithAll(): BaseResponse<List<HabithResponse>> {
        return repository.getHabithAll()
    }

    override suspend fun getHabithOngoing(): BaseResponse<List<HabithResponse>> {
        return repository.getHabithOngoing()
    }

    override suspend fun createHabith(data: HabithResponse): BaseResponse<HabithResponse> {
        return repository.createHabith(data)
    }

    override suspend fun removeHabith(id: String): BaseResponse<HabithResponse> {
        return repository.removeHabith(id)
    }
}