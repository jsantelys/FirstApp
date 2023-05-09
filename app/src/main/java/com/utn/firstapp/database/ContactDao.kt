package com.utn.firstapp.database

import androidx.room.*
import com.utn.firstapp.entities.Contact

@Dao
interface ContactDao {

    @Query ("SELECT * FROM contact WHERE user_id=:user_id ORDER BY name ASC")
    fun getContactsOrderedByName(user_id: String): MutableList<Contact>?

    @Query ("SELECT * FROM contact WHERE user_id=:user_id AND id=:id")
    fun getContactById(user_id: String,id: Int): Contact?

    @Query ("SELECT * FROM contact WHERE user_id=:user_id AND phoneNumber=:phoneNumber")
    fun getContactByPhoneNumber(user_id: String,phoneNumber: String): Contact?

    @Insert
    fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}