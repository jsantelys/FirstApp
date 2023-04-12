package com.utn.firstapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.firstapp.R
import com.utn.firstapp.entities.User

class Screen1Fragment : Fragment() {

    private var users : MutableList<User> = mutableListOf()

    private lateinit var btnNavigate: Button
    private lateinit var inputName : EditText
    private lateinit var inputPassword : EditText
    private lateinit var v: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_screen1, container, false)
        btnNavigate = v.findViewById(R.id.btnNavigate)
        inputName = v.findViewById(R.id.NameField)
        inputPassword = v.findViewById(R.id.PasswordField)
        generateUsers()
        return v
    }

    fun validateData(username: String, password : String) : String {
        /*when{
            user.isEmpty() -> return "Usuario vacío"
            password.isEmpty() -> return "Contraseña vacía"
        }*/

        if(username.isEmpty()) {
            return "Empty User"
        }
        if(password.isEmpty()){
            return "Empty Password"
        }
        return "OK"
    }

    private fun logIn(username: String, password: String): String {
        return if(validateData(username,password) == "OK"){
            if(users.any { user -> (user.name == username && user.password == password) })
                "OK"
            else "User not found"
        } else {
            validateData(username, password)
        }
    }

    override fun onStart() {
        super.onStart()

        btnNavigate.setOnClickListener {
            val response = logIn(inputName.text.toString(),inputPassword.text.toString())

            if(response == "OK") {
                val action = Screen1FragmentDirections.actionScreen1FragmentToScreen2Fragment()
                findNavController().navigate(action)
            }
            else {
                Snackbar.make(v, response, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        inputName.setText("")
        inputPassword.setText("")
    }

    fun generateUsers () {
        users.add(User("Javier", "Santelys", "j@jmail.com", "1234"))
        users.add(User("Lucas", "Sidoti", "j@jmail.com", "1234"))
        users.add(User("Alejandro", "Torres", "j@jmail.com", "1234"))
        users.add(User("Humberto", "Delgado", "j@jmail.com", "1234"))
    }

}