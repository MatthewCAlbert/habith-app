package com.bncc.habith.ui.view.habithongoing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.util.UserPref
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

            if (response.success)
                habithLiveData.value = response.data!!
        }
    }

    fun getHabith(): LiveData<List<HabithResponse>> = habithLiveData
}