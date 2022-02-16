package com.bncc.habith.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.data.remote.response.HabithResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OngoingViewModel @Inject constructor(
    private val repository: HabithRepositoryImpl
) : ViewModel() {

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