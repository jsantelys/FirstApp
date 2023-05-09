package com.utn.firstapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.firstapp.R
import com.utn.firstapp.activities.AppActivity
import com.utn.firstapp.database.AppDatabase
import com.utn.firstapp.database.UserDao
import com.utn.firstapp.entities.User

class LoginFragment : Fragment() {

    private var db: AppDatabase? = null
    private var userDao: UserDao? = null

    private lateinit var usersList : MutableList<User>

    private lateinit var btnNavigate: Button
    private lateinit var inputName : EditText
    private lateinit var inputPassword : EditText
    private lateinit var v: View

    private lateinit var tvRegister : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        btnNavigate = v.findViewById(R.id.btnNavigate)
        inputName = v.findViewById(R.id.NameField)
        inputPassword = v.findViewById(R.id.PasswordField)
        tvRegister = v.findViewById(R.id.tvRegister)
        return v
    }

    private fun validData() : Boolean {
        /*when{
            user.isEmpty() -> return "Usuario vacío"
            password.isEmpty() -> return "Contraseña vacía"
        }*/

        if(inputName.text.toString().isEmpty()) {
            inputName.error = "Username is empty"
            return false
        }
        if(inputPassword.text.toString().isEmpty()){
            inputPassword.error = "Password is empty"
            return false
        }
        return true
    }

    private fun logIn(): User? {
            return userDao?.getUserByLogIn(inputName.text.toString(),
                inputPassword.text.toString())
    }

    override fun onStart() {
        super.onStart()
        db = AppDatabase.getInstance(requireContext())
        userDao= db?.userDao()
    }

    override fun onResume() {
        super.onResume()

        btnNavigate.setOnClickListener {
            if(validData())
            {
                var user = logIn()
                if(user!=null) {
                    activity?.let {
                        val intent = Intent(it, AppActivity::class.java).putExtra("user_id",user.id)
                        activity?.startActivity(intent)
                        activity?.finish()
                    }
                }
                else {
                    inputPassword.error = "Incorrect password or user doesn't exists"
                }
            }
        }
        tvRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
        inputName.addTextChangedListener {
            inputName.error = null
        }
        inputPassword.addTextChangedListener {
            inputPassword.error = null
        }
    }

}