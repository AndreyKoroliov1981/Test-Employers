package com.korol.employers.ui

import com.korol.employers.models.EmployersModel
import com.korol.employers.models.EmployersType
import com.korol.employers.repository.server.ServerRepository

class DataFromServer(private val serverRepository: ServerRepository) {
    suspend fun get100employersNoType(text:String,onlyWithVacancies:Boolean): EmployersModel {
        return serverRepository.get100employersNoType(text,onlyWithVacancies)
    }

    suspend fun get100employersWithType(text:String,type:List<String>,onlyWithVacancies:Boolean): EmployersModel {
        return serverRepository.get100employersWithType(text,type,onlyWithVacancies)
    }

    suspend fun getEmployersType(): EmployersType {
        return serverRepository.getEmployersType()
    }
}