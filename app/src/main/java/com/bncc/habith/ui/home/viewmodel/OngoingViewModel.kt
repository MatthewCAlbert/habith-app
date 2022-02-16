package com.bncc.habith.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.HabithRepository
import com.bncc.habith.data.remote.response.HabithResponse
import kotlinx.coroutines.launch

class OngoingViewModel : ViewModel() {

    private val repository = HabithRepository()

    private val habithLiveData = MutableLiveData<List<HabithResponse>>()

    fun fetchHabith() {
        viewModelScope.launch {
            val response = repository.getHabithOngoing()

            if (response.isNotEmpty())
                habithLiveData.postValue(response)
        }
    }

    fun getHabith(): LiveData<List<HabithResponse>> = habithLiveData
}