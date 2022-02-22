package com.bncc.habith.ui.view.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.domain.interactor.AuthInteractor
import com.bncc.habith.domain.usecase.ClearSessionUseCase
import com.bncc.habith.domain.usecase.GetIsLoginUseCase
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val clearSessionUseCase: ClearSessionUseCase,
    private val interactor: AuthInteractor
) : ViewModel() {

    private val _viewState = MutableLiveData("loading")
    private val _userDetail = MutableLiveData<UserResponse>()

    fun getUserDetail() {
        viewModelScope.launch {
            val response = interactor.toDetail()

            if (response!!.success){
                _userDetail.value = response!!
                _viewState.value = "success"
            }
        }
    }

    fun logout() = clearSessionUseCase.invoke()

    fun userDetail(): LiveData<UserResponse> = _userDetail

    fun viewState(): LiveData<String> = _viewState

}