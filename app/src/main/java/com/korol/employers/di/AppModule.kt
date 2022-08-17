package com.korol.employers.di

import android.content.Context
import com.korol.employers.ui.DataFromServer
import com.korol.employers.ui.DataFromStore
import com.korol.employers.ui.splash.SplashViewModelFactory
import com.korol.employers.ui.work.filters.FiltersViewModelFactory
import com.korol.employers.ui.work.list.ListEmployersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideSplashViewModelFactory(dataFromServer: DataFromServer,dataFromStore: DataFromStore): SplashViewModelFactory {
        return SplashViewModelFactory(dataFromServer = dataFromServer, dataFromStore = dataFromStore)
    }

    @Provides
    fun provideListEmployersViewModelFactory(dataFromServer: DataFromServer,dataFromStore: DataFromStore): ListEmployersViewModelFactory {
        return ListEmployersViewModelFactory(dataFromServer = dataFromServer, dataFromStore = dataFromStore)
    }

    @Provides
    fun provideFiltersViewModelFactory(dataFromStore: DataFromStore): FiltersViewModelFactory {
        return FiltersViewModelFactory(dataFromStore = dataFromStore)
    }

}