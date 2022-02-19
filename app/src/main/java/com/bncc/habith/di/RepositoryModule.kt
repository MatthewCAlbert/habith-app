package com.bncc.habith.di

import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.data.remote.network.ApiService
import com.bncc.habith.data.repository.HabithRepository
import com.bncc.habith.util.UserPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService
    ): HabithRepository = HabithRepositoryImpl(apiService)
}