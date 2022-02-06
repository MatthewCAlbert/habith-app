package com.bncc.habith.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    private val isSuccess = MutableLiveData<Boolean>()

    fun register(username: String, email: String, password: String){
        isSuccess.value = true
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}