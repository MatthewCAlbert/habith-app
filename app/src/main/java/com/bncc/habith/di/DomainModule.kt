package com.bncc.habith.di

import com.bncc.habith.data.repository.HabithRepository
import com.bncc.habith.domain.interactor.*
import com.bncc.habith.domain.usecase.*
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
        repo: HabithRepository
    ): AuthInteractor = AuthInteractorImpl(repo)

    @Singleton
    @Provides
    fun provideGetIsLoginUseCase(
        pref: UserPref
    ): GetIsLoginUseCase = GetIsLoginUseCaseImpl(pref)

    @Singleton
    @Provides
    fun provideSetTokenUseCase(
        pref: UserPref
    ): SetTokenUseCase = SetTokenUseCaseImpl(pref)

    @Singleton
    @Provides
    fun provideClearSessionUseCase(
        pref: UserPref
    ): ClearSessionUseCase = ClearSessionUseCaseImpl(pref)
}