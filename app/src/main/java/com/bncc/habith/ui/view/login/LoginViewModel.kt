package com.bncc.habith.ui.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.domain.interactor.AuthInteractor
import com.bncc.habith.domain.usecase.GetIsLoginUseCase
import com.bncc.habith.domain.usecase.SetTokenUseCase
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    private val interactor: AuthInteractor
) : ViewModel() {

    private val _viewState = MutableLiveDataStatus<UserResponse>()

    fun login(username: String, password: String) = viewModelScope.launch {
        _viewState.postLoading()
        try {
            val response = interactor.toLogin(username, password)
            delay(1500)

            if (response == null) {
                _viewState.postEmpty()
            } else {
                setTokenUseCase.invoke(response.data.token!!)
                _viewState.postSuccess(response)
            }
        } catch (e: Exception) {
            _viewState.postError(e)
        }
    }

    fun viewState(): LiveDataStatus<UserResponse> = _viewState
}