package com.korol.employers.ui.work.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.korol.employers.models.Employer
import com.korol.employers.models.EmployersModel
import com.korol.employers.ui.DataFromServer
import com.korol.employers.ui.DataFromStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListEmployersViewModel(
    private val dataFromServer: DataFromServer,
    private val dataFromStore: DataFromStore
) : ViewModel() {

    private var job: Job? = null
    private var _listEmployer = MutableLiveData<List<Employer>>().apply {
        value = listOf()
    }
    var listEmployer: LiveData<List<Employer>> = _listEmployer

    private var searchText: String = ""
    private var hasVacancy: Boolean = false
    private var searchEmployersType = emptyList<String>()

    init {
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

    fun saveSearchText(text: String) {
        viewModelScope.launch {
            dataFromStore.saveSearchText(text)
            fillAdapter()
        }
    }

    fun getSearchText(): String = searchText

    fun loadHasVacancy(): Boolean = hasVacancy

    fun fillAdapter() {
        job?.cancel()
        job = viewModelScope.launch {
            val employersModel: EmployersModel
            if (searchEmployersType.isEmpty()) {
                employersModel = dataFromServer.get100employersNoType(searchText, hasVacancy)
            } else {
                val getType = mutableListOf<String>()
                val type = object : TypeToken<HashMap<String, String>>() {}.type
                for (i in searchEmployersType.indices) {
                    val testHashMap2: HashMap<String, String> =
                        Gson().fromJson(searchEmployersType[i], type)
                    getType.add(testHashMap2.get("id")!!)
                }
                employersModel =
                    dataFromServer.get100employersWithType(searchText, getType, hasVacancy)
            }

            _listEmployer.postValue(employersModel.items as List<Employer>?)
        }
    }

}