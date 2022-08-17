package com.korol.employers.api

object Common {
    private val BASE_URL="https://api.hh.ru/"
    val retrofitService: RetrofitServices
        get()= RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}