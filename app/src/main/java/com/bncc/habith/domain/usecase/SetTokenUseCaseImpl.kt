package com.bncc.habith.domain.usecase

import com.bncc.habith.util.UserPref

class SetTokenUseCaseImpl(
    private val pref: UserPref
) : SetTokenUseCase {
    override fun invoke(token: String) {
        pref.setToken(token)
    }
}