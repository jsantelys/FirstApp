package com.utn.firstapp.entities

import java.util.Date

data class Contact (
    var name: String,
    var lastName: String,
    var phoneNumber: String,
    var dateAdded: String,
    var numberOfCalls: Int,
    var numberOfMessages: Int
        ){

    fun getFullName(): String {
        return "${name} ${lastName}"
    }
}