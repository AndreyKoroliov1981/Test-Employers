package com.korol.employers.repository.server

import android.util.Log
import com.korol.employers.api.Common
import com.korol.employers.api.RetrofitServices
import com.korol.employers.models.EmployersModel
import com.korol.employers.models.EmployersType
import com.korol.employers.repository.server.ServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServerRepositoryImpl() : ServerRepository {

    private val mService: RetrofitServices = Common.retrofitService

    override suspend fun get100employersNoType(text:String,onlyWithVacancies:Boolean): EmployersModel =
        withContext(Dispatchers.IO) {
            var employersModel = EmployersModel(emptyList(), -1, -1, -1, -1)
            try {
                val response = mService.get100employersNoType(text,onlyWithVacancies).execute()
                employersModel = response.body() as EmployersModel
            } catch (e: Exception) {
                Log.d("my_tag", "Retrofit don't work: ${e.message}")

            }
            return@withContext employersModel
        }

    override suspend fun get100employersWithType(text:String,type:List<String>,onlyWithVacancies:Boolean): EmployersModel =
        withContext(Dispatchers.IO) {
            var employersModel = EmployersModel(emptyList(), -1, -1, -1, -1)
            try {
                val response = mService.get100employersWithType(text,onlyWithVacancies,type).execute()
                employersModel = response.body() as EmployersModel
            } catch (e: Exception) {
                Log.d("my_tag", "Retrofit don't work: ${e.message}")

            }
            return@withContext employersModel
        }

    override suspend fun getEmployersType(): EmployersType =
        withContext(Dispatchers.IO) {
            var employersType = EmployersType(emptyList())
            try {
                val response = mService.getEmployersType().execute()
                employersType = response.body() as EmployersType
            } catch (e: Exception) {
                Log.d("my_tag", "Retrofit don't work: ${e.message}")

            }
            return@withContext employersType

        }
}