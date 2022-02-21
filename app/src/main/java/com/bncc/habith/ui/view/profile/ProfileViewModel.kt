package com.bncc.habith.ui.view.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pref: UserPref,
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val _viewState = MutableLiveData("loading")
    private val _userDetail = MutableLiveData<UserResponse>()

    fun getUserDetail() {
        viewModelScope.launch {
            val response = repo.getUserDetail(pref.getToken()!!)
            Log.d("GetUserDetail", response.toString())

            if (response.success){
                _userDetail.value = response.data!!
                _viewState.value = "success"
            }
        }
    }

    fun logout() = pref.clearSession()

    fun userDetail(): LiveData<UserResponse> = _userDetail

    fun viewState(): LiveData<String> = _viewState

}