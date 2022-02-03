package com.bncc.habith.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val isSuccess = MutableLiveData<Boolean>()

    fun login(username: String, password: String){
        if (username == "test" && password == "test123")
            isSuccess.postValue(true)
        else
            isSuccess.postValue(false)
    }
}