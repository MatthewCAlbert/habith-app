package com.bncc.habith.ui.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.domain.usecase.SetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val isSuccess = MutableLiveData<Boolean>()

    fun register(username: String, email: String, password: String, name: String) {
        viewModelScope.launch {
            val response = repo.toRegister(email, username, password, name)

            if (response!!.success) {
                isSuccess.value = true
                setTokenUseCase.invoke(response.data.token!!)
            } else {
                isSuccess.value = false
            }
        }
    }

    fun getIsSuccess(): LiveData<Boolean> = isSuccess
}