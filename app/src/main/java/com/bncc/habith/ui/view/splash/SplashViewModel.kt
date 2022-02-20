package com.bncc.habith.ui.view.splash

import androidx.lifecycle.ViewModel
import com.bncc.habith.domain.usecase.GetIsLoginUseCase
import com.bncc.habith.util.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getIsLoginUseCase: GetIsLoginUseCase
) : ViewModel() {

    fun isLogin(): Boolean {
        return getIsLoginUseCase.invoke()
    }
}