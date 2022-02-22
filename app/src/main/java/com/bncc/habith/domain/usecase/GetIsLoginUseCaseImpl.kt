package com.bncc.habith.domain.usecase

import com.bncc.habith.util.UserPref

class GetIsLoginUseCaseImpl(
    private val pref: UserPref
): GetIsLoginUseCase {
    override fun invoke(): Boolean {
        return pref.isLogin()
    }
}