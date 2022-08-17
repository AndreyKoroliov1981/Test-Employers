package com.korol.employers.ui.work.filters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korol.employers.ui.DataFromStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FiltersViewModel(private val dataFromStore: DataFromStore) : ViewModel() {

    private var searchText: String = ""
    private var hasVacancy: Boolean = false
    var searchEmployersType = emptyList<String>()
    var loadEmployersType = emptyList<String>()

    private var _updateUI = MutableLiveData<Boolean>().apply {
        value = false
    }
    var updateUI: LiveData<Boolean> = _updateUI


    init {

        viewModelScope.launch {
            dataFromStore.loadEmployersType.collectLatest {
                loadEmployersType = it
            }
        }

        viewModelScope.launch {
            dataFromStore.loadSearchText.collectLatest {
                searchText = it
            }
        }

        viewModelScope.launch {
            dataFromStore.loadHasVacancy.collectLatest {
                hasVacancy = it
            }
        }

        viewModelScope.launch {
            dataFromStore.loadSearchEmployersType.collectLatest {
                searchEmployersType = it
            }
        }

    }

    fun getSearchText(): String = searchText

    fun saveSearchText(text:String) {
        viewModelScope.launch {
            dataFromStore.saveSearchText(text)
        }
    }

    fun getHasVacancy(): Boolean = hasVacancy

    fun saveHasVacancy(openVacancy:Boolean) {
        viewModelScope.launch {
            dataFromStore.saveHasVacancy(openVacancy)
        }
    }

    fun saveSearchEmployersType(list:List<String>) {
        viewModelScope.launch {
            dataFromStore.saveSearchEmployersType(list)
        }
    }

    fun reset(){
        viewModelScope.launch {
            dataFromStore.saveSearchEmployersType(emptyList())
            dataFromStore.saveHasVacancy(false)
            dataFromStore.saveSearchText("")
            _updateUI.value=true
            _updateUI.value=false
        }



    }
}
