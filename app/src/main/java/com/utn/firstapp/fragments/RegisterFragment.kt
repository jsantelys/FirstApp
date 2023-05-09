package com.utn.firstapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.utn.firstapp.R
import com.utn.firstapp.database.AppDatabase
import com.utn.firstapp.database.UserDao
import com.utn.firstapp.entities.User

class RegisterFragment : Fragment() {

    private var db: AppDatabase ? = null
    private var userDao : UserDao ? = null

    private lateinit var v: View

    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView

    private lateinit var etLayoutName : TextInputLayout
    private lateinit var inputName: TextInputEditText
    private lateinit var etLayoutLastName : TextInputLayout
    private lateinit var inputLastName : TextInputEditText
    private lateinit var etLayoutEmail : TextInputLayout
    private lateinit var inputEmail: TextInputEditText
    private lateinit var etLayoutUsername: TextInputLayout
    private lateinit var inputUsername : TextInputEditText
    private lateinit var etLayoutPassword: TextInputLayout
    private lateinit var inputPassword : TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_register, container, false)
        btnRegister = v.findViewById(R.id.btnRegister)
        tvLogin = v.findViewById(R.id.tvLogin)
        inputName = v.findViewById(R.id.inputTextName)
        inputLastName = v.findViewById(R.id.inputTextLastName)
        inputEmail = v.findViewById(R.id.inputTextEmail)
        inputUsername = v.findViewById(R.id.inputTextUsername)
        inputPassword = v.findViewById(R.id.inputTextPassword)
        etLayoutName = v.findViewById(R.id.etLayoutName)
        etLayoutLastName = v.findViewById(R.id.etLayoutLastName)
        etLayoutEmail = v.findViewById(R.id.etLayoutEmail)
        etLayoutUsername = v.findViewById(R.id.etLayoutUsername)
        etLayoutPassword = v.findViewById(R.id.etLayoutPassword)
        return v
    }

    override fun onStart() {
        db = AppDatabase.getInstance(requireContext())
        userDao = db?.userDao()
        super.onStart()
    }

    private fun validateForm() : Boolean {
        var valid = true
        if (inputName.text.toString().isBlank()){
            etLayoutName.error = "Please input your name"
            valid = false
        }
        if (inputLastName.text.toString().isBlank()){
            etLayoutLastName.error = "Please input your last Name"
            valid = false
        }
        if (inputEmail.text.toString().isBlank()){
            etLayoutEmail.error = "Please input your email"
            valid = false
        }
        if (inputUsername.text.toString().isBlank()){
            etLayoutUsername.error = "Please input your username"
            valid = false
        }
        if (inputPassword.text.toString().isBlank()){
            etLayoutPassword.error = "Please input your password"
            valid = false
        }
        return valid
    }

    private fun registerUser(): Boolean {
        var couldRegister = true

        val existsEmail = userDao?.getUserByEmail(inputEmail.text.toString())
        val existsUsername = userDao?.getUserByUsername(inputUsername.text.toString())

        if(existsEmail != null) {
            etLayoutEmail.error = "This email is already in use"
            couldRegister = false
        }
        if(existsUsername != null) {
            etLayoutUsername.error = "This username is already in use"
            couldRegister = false
        }

        if(existsEmail == null && existsUsername == null) {
            userDao?.insertUser(User(0, inputName.text.toString(),inputLastName.text.toString(),
                inputUsername.text.toString(),inputEmail.text.toString(),inputPassword.text.toString()))
        }
        return couldRegister
    }

    override fun onResume() {
        super.onResume()
        btnRegister.setOnClickListener {
            if (validateForm())
                if(registerUser())
                {
                    Toast.makeText(requireContext(),"Registered successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
        }

        tvLogin.setOnClickListener {
            findNavController().navigateUp()
        }
        inputName.addTextChangedListener {
            etLayoutName.error = null
        }
        inputLastName.addTextChangedListener {
            etLayoutLastName.error = null
        }
        inputEmail.addTextChangedListener {
            etLayoutEmail.error = null
        }
        inputUsername.addTextChangedListener {
            etLayoutUsername.error = null
        }
        inputPassword.addTextChangedListener {
            etLayoutPassword.error = null
        }
    }

}