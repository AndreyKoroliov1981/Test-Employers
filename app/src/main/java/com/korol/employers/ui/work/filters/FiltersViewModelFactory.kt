package com.korol.employers.ui.work.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.korol.employers.ui.DataFromStore

class FiltersViewModelFactory (val dataFromStore: DataFromStore
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FiltersViewModel(dataFromStore=dataFromStore) as T
    }
}