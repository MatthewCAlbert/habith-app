package com.bncc.habith.ui.view.splash

import androidx.lifecycle.ViewModel
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref: UserPref
) : ViewModel() {

    fun isLogin(): Boolean {
        return pref.isLogin()
    }
}