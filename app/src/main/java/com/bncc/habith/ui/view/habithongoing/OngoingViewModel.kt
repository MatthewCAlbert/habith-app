package com.bncc.habith.ui.view.habithongoing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OngoingViewModel @Inject constructor(
    private val repo: HabithRepositoryImpl,
) : ViewModel() {

    private val _habithLiveData = MutableLiveDataStatus<List<HabithResponse.Data>>()

    fun fetchHabith() = viewModelScope.launch {
        _habithLiveData.postLoading()
        try {
            val response = repo.getHabithOngoing()

            if (response!!.data.isNullOrEmpty()) _habithLiveData.postEmpty()
            else _habithLiveData.postSuccess(response.data!!)

        } catch (e: Exception) {
            _habithLiveData.postError(e)
        }
    }

    fun getHabith(): LiveDataStatus<List<HabithResponse.Data>> = _habithLiveData
}