package com.korol.employers.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.korol.employers.ui.DataFromServer
import com.korol.employers.ui.DataFromStore

class SplashViewModelFactory(val dataFromServer: DataFromServer,
                             val dataFromStore: DataFromStore
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(dataFromServer = dataFromServer,
            dataFromStore=dataFromStore) as T
    }
}