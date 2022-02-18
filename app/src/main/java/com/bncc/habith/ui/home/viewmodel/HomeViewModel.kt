package com.bncc.habith.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pref: UserPref
) : ViewModel() {

    fun logout(){
        pref.clearSession()
    }
}