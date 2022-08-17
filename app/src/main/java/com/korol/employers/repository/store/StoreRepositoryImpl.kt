package com.korol.employers.repository.store

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("saveEmployersType")

class StoreRepositoryImpl(context: Context):StoreRepository {

    private val dataStore = context.dataStore

    companion object{
        val EMPLOYERS_TYPE = stringSetPreferencesKey("EMPLOYERS_TYPE")
        val SEARCH_TEXT = stringPreferencesKey("SEARCH_TEXT")
        val HAS_VACANCY = booleanPreferencesKey("HAS_VACANCY")
        val SEARCH_EMPLOYERS_TYPE = stringSetPreferencesKey("SEARCH_EMPLOYERS_TYPE")

    }

    override suspend fun saveEmployersType(list:List<String>){
        dataStore.edit {
            it[EMPLOYERS_TYPE]=list.toSet()
        }
    }

    override val loadEmployersType: Flow<List<String>> = dataStore.data.map {
        it[EMPLOYERS_TYPE]?.toList() ?: emptyList()
    }

    override suspend fun saveSearchText(text:String){
        dataStore.edit {
            it[SEARCH_TEXT]=text
        }
    }

    override val loadSearchText: Flow<String> = dataStore.data.map {
        it[SEARCH_TEXT] ?: ""
    }

    override suspend fun saveHasVacancy(hasVacancy:Boolean){
        dataStore.edit {
            it[HAS_VACANCY]=hasVacancy
        }
    }

    override val loadHasVacancy: Flow<Boolean> = dataStore.data.map {
        it[HAS_VACANCY] ?: false
    }

    override suspend fun saveSearchEmployersType(list:List<String>){
        dataStore.edit {
            it[SEARCH_EMPLOYERS_TYPE]=list.toSet()
        }
    }

    override val loadSearchEmployersType: Flow<List<String>> = dataStore.data.map {
        it[SEARCH_EMPLOYERS_TYPE]?.toList() ?: emptyList()
    }

}