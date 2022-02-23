package com.bncc.habith.di

import com.bncc.habith.data.remote.network.api.ApiClient
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.data.remote.network.api.ApiService
import com.bncc.habith.data.repository.HabithRepository
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
        apiClient: ApiClient
    ): HabithRepository = HabithRepositoryImpl(apiClient)
}