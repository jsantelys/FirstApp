package com.utn.firstapp.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.firstapp.entities.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartingContacts(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch{
            Log.d("Starting Contacts", "Pre-populating database...")
            fillWithStartingContacts(context)
        }
    }

    private fun fillWithStartingContacts(context: Context)
    {
        val contacts = listOf(
            Contact(0, 1,"Javier","Santelys", "+5491124044250",
                "01/05/2023",365,2325),
            Contact(0, 1,"Francisco","Santelys", "+5491124022222",
                "01/05/2023",365,2325),
            Contact(0, 1,"Francisco Javier","Santelys", "+5491124022222",
                "01/05/2023",365,2325),
        )
        val dao = AppDatabase.getInstance(context)?.contactDao()

        contacts.forEach {
            dao?.insertContact(it)
        }
    }
}