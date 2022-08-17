package com.korol.employers.ui

import com.korol.employers.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow

class DataFromStore(private val storeRepository: StoreRepository) {
    suspend fun saveEmployersType(list:List<String>) {
        return storeRepository.saveEmployersType(list)
    }

    val loadEmployersType: Flow<List<String>> = storeRepository.loadEmployersType


    suspend fun saveSearchText(text:String) {
        storeRepository.saveSearchText(text)
    }

    val loadSearchText: Flow<String> = storeRepository.loadSearchText


    suspend fun saveHasVacancy(hasVacancy:Boolean) {
        storeRepository.saveHasVacancy(hasVacancy)
    }

    val loadHasVacancy: Flow<Boolean> = storeRepository.loadHasVacancy

    suspend fun saveSearchEmployersType(list:List<String>) {
        storeRepository.saveSearchEmployersType(list)
    }

    val loadSearchEmployersType: Flow<List<String>> = storeRepository.loadSearchEmployersType
}