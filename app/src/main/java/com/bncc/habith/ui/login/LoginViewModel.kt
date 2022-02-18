package com.bncc.habith.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val isSuccess = MutableLiveData<Boolean>()

    fun login(username: String, password: String){
        isSuccess.value = username == "test" && password == "test123"
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}