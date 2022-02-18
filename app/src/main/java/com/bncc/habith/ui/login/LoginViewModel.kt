package com.bncc.habith.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val isSuccess = MutableLiveData<Boolean>()

    fun login(username: String, password: String){
        isSuccess.value = username == "test" && password == "test123"
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}