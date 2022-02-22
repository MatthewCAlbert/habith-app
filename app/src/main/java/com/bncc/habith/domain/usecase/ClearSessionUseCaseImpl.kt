package com.bncc.habith.domain.usecase

import com.bncc.habith.util.UserPref

class ClearSessionUseCaseImpl(
    private val pref: UserPref
): ClearSessionUseCase {
    override fun invoke() {
        pref.clearSession()
    }
}