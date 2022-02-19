package com.bncc.habith.ui.view.login

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
class LoginViewModel @Inject constructor(
    private val pref: UserPref,
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val isSuccess = MutableLiveData(false)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = repo.toLogin(username, password)

            if (response.success){
                isSuccess.value = true
                pref.setToken(response.data?.token!!)
            }

        }
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}