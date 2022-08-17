package com.korol.employers.repository.store

import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun saveEmployersType(list:List<String>)

    val loadEmployersType: Flow<List<String>>

    suspend fun saveSearchText(text:String)

    val loadSearchText: Flow<String>

    suspend fun saveHasVacancy(hasVacancy:Boolean)

    val loadHasVacancy: Flow<Boolean>

    suspend fun saveSearchEmployersType(list:List<String>)

    val loadSearchEmployersType: Flow<List<String>>

}