package com.korol.employers.di

import com.korol.employers.repository.server.ServerRepository
import com.korol.employers.repository.store.StoreRepository
import com.korol.employers.ui.DataFromServer
import com.korol.employers.ui.DataFromStore
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideDataFromServer(serverRepository: ServerRepository): DataFromServer{
        return DataFromServer(serverRepository = serverRepository)
    }
    @Provides
    fun provideDataFromStore(storeRepository: StoreRepository): DataFromStore {
        return DataFromStore(storeRepository = storeRepository)
    }

}