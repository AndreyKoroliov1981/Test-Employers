package com.korol.employers.models

data class EmployersType (
    val employer_type:List<ItemDictionary>
        )

data class ItemDictionary(
        val id:String,
        val name:String
)
