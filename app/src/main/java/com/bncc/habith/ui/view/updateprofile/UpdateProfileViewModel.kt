package com.bncc.habith.ui.view.updateprofile

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
class UpdateProfileViewModel @Inject constructor(
    private val pref: UserPref,
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val _viewState = MutableLiveData<String>()

    fun updateProfile(name: String) {
        viewModelScope.launch {
            val response = repo.updateUserDetail(pref.getToken()!!, name)
            _viewState.value = "loading"

            if (response.success) {
                _viewState.value = "success"
            } else {
                _viewState.value = "failed"
            }
        }
    }

    fun viewState(): LiveData<String> = _viewState
}