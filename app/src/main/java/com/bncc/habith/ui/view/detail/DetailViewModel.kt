package com.bncc.habith.ui.view.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: HabithRepositoryImpl
) : ViewModel() {
    lateinit var targetType: String
    var targetNum = 0
    var targetProgress = 0

    fun increaseTargetProgress(): Int {
        return if (targetProgress < targetNum) {
            ++targetProgress
        } else {
            targetProgress
        }
    }

    fun decreaseTargetProgress(): Int {
        return if (targetProgress > 0) {
            --targetProgress
        } else {
            targetProgress
        }
    }

    private val _viewState = MutableLiveDataStatus<HabithResponse>()

    fun remove(id: String) = viewModelScope.launch {
        _viewState.postLoading()
        try {
            delay(1500)
            val response = repo.removeHabith(id)
            if (response!!.success) _viewState.postSuccess(response)
        } catch (e: Exception) {
            _viewState.postError(e)
        }
    }

    fun viewState(): LiveDataStatus<HabithResponse> = _viewState
}