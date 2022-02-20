package com.bncc.habith.ui.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.domain.interactor.HabitInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val interactor: HabitInteractor
): ViewModel() {
    lateinit var targetType: String
    var targetNum = 0
    var targetProgress = 0
    private val _isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun increaseTargetProgress(): Int{
        return if(targetProgress<targetNum){
            ++targetProgress
        }else{
            targetProgress
        }
    }

    fun deleteHabith(id: String){
        viewModelScope.launch {
            val response = interactor.removeHabith(id)
            _isSuccess.value = response.success
        }
    }

    fun decreaseTargetProgress(): Int{
        return if(targetProgress>0){
            --targetProgress
        }else{
            targetProgress
        }
    }

    fun isSuccess(): LiveData<Boolean> = _isSuccess
}