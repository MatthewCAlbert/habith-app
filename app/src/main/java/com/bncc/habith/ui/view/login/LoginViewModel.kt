package com.bncc.habith.ui.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.domain.interactor.AuthInteractor
import com.bncc.habith.domain.usecase.SetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    private val interactor: AuthInteractor
) : ViewModel() {

    private val isSuccess = MutableLiveData(false)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = interactor.toLogin(username, password)

            if (response.success){
                isSuccess.value = true
                setTokenUseCase.invoke(response.data?.token!!)
            }

        }
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}