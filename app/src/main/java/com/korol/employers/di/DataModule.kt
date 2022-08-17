package com.korol.employers.di

import android.content.Context
import com.korol.employers.repository.server.ServerRepository
import com.korol.employers.repository.server.ServerRepositoryImpl
import com.korol.employers.repository.store.StoreRepository
import com.korol.employers.repository.store.StoreRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideServerRepository(): ServerRepository {
        return ServerRepositoryImpl()
    }

    @Provides
    fun provideStoreRepository(context: Context): StoreRepository {
        return StoreRepositoryImpl(context = context)
    }
}