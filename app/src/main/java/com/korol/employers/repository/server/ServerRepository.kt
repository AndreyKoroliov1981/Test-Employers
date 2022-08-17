package com.korol.employers.repository.server

import com.korol.employers.models.EmployersModel
import com.korol.employers.models.EmployersType

interface ServerRepository {
    suspend fun get100employersNoType(text:String,onlyWithVacancies:Boolean): EmployersModel

    suspend fun get100employersWithType(text:String,type:List<String>,onlyWithVacancies:Boolean): EmployersModel

    suspend fun getEmployersType(): EmployersType

}