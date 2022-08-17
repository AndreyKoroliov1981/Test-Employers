package com.korol.employers.ui.splash

import android.util.Log
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.korol.employers.models.EmployersType
import com.korol.employers.repository.store.StoreRepositoryImpl.Companion.EMPLOYERS_TYPE
import com.korol.employers.ui.DataFromServer
import com.korol.employers.ui.DataFromStore
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class SplashViewModel(private val dataFromServer: DataFromServer,
                      private val dataFromStore: DataFromStore
): ViewModel() {

    private lateinit var jobEmployersType: Deferred<EmployersType>

    private var _error = MutableLiveData<Boolean>()
    var error: LiveData<Boolean> = _error

    private var _startWorkActivity = MutableLiveData<Boolean>()
    var startWorkActivity: LiveData<Boolean> = _startWorkActivity

    init {
        getEmployersType()
    }

    fun getEmployersType(){
        _error.value=false
        viewModelScope.launch {
            jobEmployersType = viewModelScope.async {
                dataFromServer.getEmployersType()
            }
            val dataFromInternet = jobEmployersType.await()

            if (dataFromInternet.employer_type.isEmpty()) {
                Log.d("tag","internet don`t work")
                dataFromStore.loadEmployersType.collectLatest{
                    if (it.isEmpty()) {
                        _error.value=true
                    } else {
                        _startWorkActivity.value=true
                    }
                }

            } else {
                val listEmployersType=(0 until dataFromInternet.employer_type.size).map {
                    val testHashMap=HashMap<String,String>(2)
                    testHashMap.put("id",dataFromInternet.employer_type[it].id)
                    testHashMap.put("name",dataFromInternet.employer_type[it].name)
                    val hashMapString = Gson().toJson(testHashMap)
                    hashMapString
//                    dataFromInternet.employer_type[it].name
                }
                dataFromStore.saveEmployersType(listEmployersType)
                _startWorkActivity.value=true
            }
        }
    }

}