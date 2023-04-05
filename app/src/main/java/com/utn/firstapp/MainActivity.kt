package com.utn.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.utn.firstapp.entities.User

class MainActivity : AppCompatActivity() {

    /*private var newUser: User = User("Javier", "Santelys", "j@jmail.com", "1234")

    private var users : MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", newUser.greet())

        users.add(User("Javier", "Santelys", "j@jmail.com", "1234"))
        users.add(User("Lucas", "Sidoti", "j@jmail.com", "1234"))
        users.add(User("Alejandro", "Torres", "j@jmail.com", "1234"))
        users.add(User("Humberto", "Delgado", "j@jmail.com", "1234"))

        users.forEach { currentUser ->
            Log.d("MainActivity", currentUser.greet())
        }
        users.find { currentUser ->
            currentUser.name == "Javier"
        }?.let { currentUser->
            Log.d("MainActivity", currentUser.name)
        }

    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}