package com.bncc.habith.di

import com.bncc.habith.data.repository.HabithRepository
import com.bncc.habith.domain.interactor.AuthInteractor
import com.bncc.habith.domain.interactor.AuthInteractorImpl
import com.bncc.habith.domain.interactor.HabitInteractor
import com.bncc.habith.domain.interactor.HabitInteractorImpl
import com.bncc.habith.domain.usecase.GetIsLoginUseCase
import com.bncc.habith.domain.usecase.GetIsLoginUseCaseImpl
import com.bncc.habith.domain.usecase.SetTokenUseCase
import com.bncc.habith.domain.usecase.SetTokenUseCaseImpl
import com.bncc.habith.util.UserPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideAuthInteractor(
        habithRepository: HabithRepository
    ): AuthInteractor {
        return AuthInteractorImpl(habithRepository)
    }

    @Singleton
    @Provides
    fun provideHabithInteractor(
        habithRepository: HabithRepository
    ): HabitInteractor {
        return HabitInteractorImpl(habithRepository)
    }

    @Singleton
    @Provides
    fun provideGetIsLoginUseCase(
        pref: UserPref
    ): GetIsLoginUseCase {
        return GetIsLoginUseCaseImpl(pref)
    }

    @Singleton
    @Provides
    fun provideSetTokenUseCase(
        pref: UserPref
    ): SetTokenUseCase {
        return SetTokenUseCaseImpl(pref)
    }
}