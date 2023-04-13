package com.utn.firstapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
@Parcelize
class Contact (
    var name: String,
    var lastName: String,
    var phoneNumber: String,
    var dateAdded: String,
    var numberOfCalls: Int,
    var numberOfMessages: Int
        ) : Parcelable{

    fun getFullName(): String {
        return "${name} ${lastName}"
    }
}