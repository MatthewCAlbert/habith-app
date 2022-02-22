package com.bncc.habith.ui.view.updateprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val _viewState = MutableLiveDataStatus<UserResponse>()

    fun updateProfile(name: String) {
        viewModelScope.launch {
            _viewState.postLoading()
            try {
                val response = repo.updateUserDetail(name)
                if (response!!.success)
                    _viewState.postSuccess(response)

            }catch (e: Exception){
                _viewState.postError(e)
            }
        }
    }

    fun viewState(): LiveDataStatus<UserResponse> = _viewState
}