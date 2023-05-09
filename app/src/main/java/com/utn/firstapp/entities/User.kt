package com.utn.firstapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/*class User {
    var name: String
    var lastName: String
    var email: String
    var password: String

    constructor(name: String, lastName: String, email: String, password: String) {
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
    }
}*/

/*class User (
    var name: String,
    var lastName: String,
    var email: String,
    var password: String) {

    var age: Int = 0

    fun greet(): String {
        return "Hi, my name is $name $lastName"
    }

    companion object{
        const val MAX_AGE = 100                     //Investigate why const val
    }
}*/
@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String)
    /*override fun greet(): String {
        return "Hi, my name is $name $lastName"
    }*/