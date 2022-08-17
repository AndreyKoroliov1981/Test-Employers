package com.korol.employers.models

data class EmployersModel(
    val items:List<Employer>,
    val found: Int,
    val pages: Int,
    val per_page: Int,
    val page: Int
)

data class Employer(
    val id: String,
    val name: String,
    val url: String,
    val alternate_url: String,
    val logo_urls: ListUrls?,
    val vacancies_url: String,
    val open_vacancies: Int
)

data class ListUrls(
    val original: String,
   val `240`: String,
   val `90`: String
)
