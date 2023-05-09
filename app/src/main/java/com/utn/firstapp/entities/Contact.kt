package com.utn.firstapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date
@Entity(tableName = "contact", foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["id"],
    childColumns = ["user_id"],
    onDelete = ForeignKey.CASCADE
)])
data class Contact (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "user_id")
    val user_id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,
    @ColumnInfo(name = "dateAdded")
    val dateAdded: String,
    @ColumnInfo(name = "numberOfCalls")
    val numberOfCalls: Int,
    @ColumnInfo(name = "numberOfMessages")
    val numberOfMessages: Int
        )
{
    fun getFullName(): String {
        return "$name $lastName"
    }
}