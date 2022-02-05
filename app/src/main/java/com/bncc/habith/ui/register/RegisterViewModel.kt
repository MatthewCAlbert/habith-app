package com.bncc.habith.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    val isSuccess = MutableLiveData<Boolean>()

    fun register(username: String, email: String, password: String){
        isSuccess.value = true
    }
}