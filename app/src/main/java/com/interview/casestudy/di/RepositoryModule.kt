package com.interview.casestudy.di

import com.interview.casestudy.network.NetworkApi
import com.interview.casestudy.repository.MainRepository
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
    fun provideMainRepository(
        NetworkApi: NetworkApi,
    ) = MainRepository(NetworkApi)
}