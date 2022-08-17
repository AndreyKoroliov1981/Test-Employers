package com.korol.employers.api

import com.korol.employers.models.EmployersModel
import com.korol.employers.models.EmployersType
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("employers?per_page=$MAX_PAGE_SIZE")
    fun get100employersWithType(
        @Query("text") text: String,
        @Query("only_with_vacancies") onlyWithVacancies: Boolean,
        @Query("type") type: List<String>
    ): Call<EmployersModel>

    @GET("employers?per_page=$MAX_PAGE_SIZE")
    fun get100employersNoType(
        @Query("text") text: String,
        @Query("only_with_vacancies") onlyWithVacancies: Boolean,
    ): Call<EmployersModel>

    @GET("dictionaries")
    fun getEmployersType(): Call<EmployersType>

    companion object {
        const val MAX_PAGE_SIZE = 100
    }

}