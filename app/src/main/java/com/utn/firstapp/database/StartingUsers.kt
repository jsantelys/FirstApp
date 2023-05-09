package com.utn.firstapp.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.firstapp.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartingUsers(private val context: Context): RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch{
            Log.d("Starting Users", "Pre-populating database...")
            fillWithStartingUsers(context)
        }
    }

    private fun fillWithStartingUsers(context: Context)
    {
        val users = listOf(
           User(0,"Javier","Santelys", "jsantely"
               ,"jasantelysdaza@frba.utn.edu.ar","1234"),
            User(0,"admin","", "admin"
                ,"","admin"),
            User(0,"test","", "test"
                ,"","password")

        )
        val dao = AppDatabase.getInstance(context)?.userDao()

        users.forEach {
            dao?.insertUser(it)
        }
    }
}
