package com.bncc.habith.ui.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.domain.interactor.AuthInteractor
import com.bncc.habith.domain.usecase.SetTokenUseCase
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    private val interactor: AuthInteractor
) : ViewModel() {

    private val _viewState = MutableLiveDataStatus<UserResponse>()

    fun register(name: String, email: String, password: String, username: String) =
        viewModelScope.launch {
            _viewState.postLoading()
            try {
                val response = interactor.toRegister(name, email, password, username)

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