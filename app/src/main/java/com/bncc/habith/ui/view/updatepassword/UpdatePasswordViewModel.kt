package com.bncc.habith.ui.view.updatepassword

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
class UpdatePasswordViewModel @Inject constructor(
    private val pref: UserPref,
    private val repo: HabithRepositoryImpl
): ViewModel(){

    private val _viewState = MutableLiveData<String>()

    fun updatePassword(old: String, new: String) {
        viewModelScope.launch {
            val response = repo.updaterUserPassword(pref.getToken()!!, old, new)
            _viewState.value = "loading"

            if (response.isSuccessful) {
                _viewState.value = "success"
            } else {
                _viewState.value = "failed"
            }
        }
    }

    fun viewState(): LiveData<String> = _viewState
}