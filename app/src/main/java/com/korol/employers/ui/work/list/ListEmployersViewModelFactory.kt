package com.korol.employers.ui.work.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.korol.employers.ui.DataFromServer
import com.korol.employers.ui.DataFromStore

class ListEmployersViewModelFactory(
    val dataFromServer: DataFromServer,
    val dataFromStore: DataFromStore
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListEmployersViewModel(
            dataFromServer = dataFromServer,
            dataFromStore = dataFromStore
        ) as T
    }
}