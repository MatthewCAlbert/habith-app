package com.bncc.habith.ui.register

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val pref: UserPref,
    private val repo: HabithRepositoryImpl
): ViewModel() {

    private val isSuccess = MutableLiveData<Boolean>(false)

    fun register(username: String, email: String, password: String, name: String){
        viewModelScope.launch {
            val response = repo.toRegister(email, username, password, name)

            if (response.success){
                isSuccess.value = true
                pref.setToken(response.data?.token!!)
            }

        }
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}