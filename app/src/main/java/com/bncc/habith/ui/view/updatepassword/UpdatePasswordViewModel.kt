package com.bncc.habith.ui.view.updatepassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdatePasswordViewModel @Inject constructor(
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val _viewState = MutableLiveDataStatus<UserResponse>()

    fun updatePassword(old: String, new: String) {
        viewModelScope.launch {
            _viewState.postLoading()
            try {
                val response = repo.updateUserPassword(old, new)
                delay(1500)

                if (response!!.success)
                    _viewState.postSuccess(response)

            } catch (e: Exception) {
                _viewState.postError(e)
            }
        }
    }

    fun viewState(): LiveDataStatus<UserResponse> = _viewState
}