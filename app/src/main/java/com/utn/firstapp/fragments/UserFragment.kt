package com.utn.firstapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.utn.firstapp.R
import com.utn.firstapp.activities.LoginActivity
import com.utn.firstapp.database.AppDatabase
import com.utn.firstapp.database.UserDao
import com.utn.firstapp.entities.User

class UserFragment : Fragment() {

    private var db: AppDatabase?=null
    private var userDao: UserDao? = null

    private lateinit var v : View
    private lateinit var btnLogout : Button


    private lateinit var etName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user, container, false)
        btnLogout = v.findViewById(R.id.btnLogout)

        db = AppDatabase.getInstance(requireContext())
        userDao= db?.userDao()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etName = view.findViewById(R.id.etNameUser)
        etLastName = view.findViewById(R.id.etLastNameUser)
        etEmail = view.findViewById(R.id.etEmailUser)
        etUsername = view.findViewById(R.id.etUsernameUser)
        etPassword = view.findViewById(R.id.etPasswordUser)
        val btnEdit = view.findViewById<FloatingActionButton>(R.id.fabEditUserData)


        val userId = requireActivity().intent.extras!!.getInt("user_id")
        val user = userDao!!.getUserById(userId.toString())

        var isEditing = false

        btnEdit.setOnClickListener{
            if(isEditing)
            {
                if(updateUser(userId,etName.text.toString(),etLastName.text.toString(),
                etEmail.text.toString(),etUsername.text.toString(),etPassword.text.toString()))
                {
                    makeFormNotEditable()
                    btnEdit.setImageResource(R.drawable.ic_baseline_edit_24)
                }
            }
            else {
                makeFormEditable()
                btnEdit.setImageResource(R.drawable.ic_baseline_check_24)
            }
            isEditing = !isEditing
        }

        etName.setText(user!!.name)
        etLastName.setText(user.lastName)
        etEmail.setText(user.email)
        etUsername.setText(user.username)
    }

    override fun onResume() {
        super.onResume()

        btnLogout.setOnClickListener {
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                activity?.startActivity(intent)
                activity?.finish()
            }
        }
        etEmail.addTextChangedListener {
            etEmail.error = null
        }
        etUsername.addTextChangedListener {
            etUsername.error = null
        }
    }

    private fun updateUser(userId: Int, inputName: String, inputLastName: String,
                           inputEmail: String, inputUsername: String, inputPassword: String): Boolean {
        val user = userDao?.getUserById(userId.toString())
        val name : String = if(user!!.name!=inputName) inputName else user.name
        val lastName : String = if(user.lastName!=inputLastName) inputLastName else user.lastName
        val email : String = if(user.email!=inputEmail) inputEmail else user.email
        val username: String = if(user.username!=inputUsername) inputUsername else user.username
        val password: String = if (inputPassword.isEmpty()) user.password else inputPassword

        val existsEmail = userDao?.getUserByEmail(inputEmail)
        val existsUsername = userDao?.getUserByUsername(inputUsername)

        if(existsEmail != null && existsEmail.email!=user.email) {
            etEmail.error = "This email is already in use"
        }
        if(existsUsername != null && existsUsername.username!=user.username) {
            etUsername.error = "This username is already in use"
        }

        if((existsEmail == null || existsEmail.email==user.email)
            && (existsUsername == null || existsUsername.username==user.username)) {
            userDao?.updateUser(User(userId,name,lastName,username,email,password))
            return true
        }
        return false
    }

    private fun makeFormEditable () {
        etName.isEnabled= true
        etName.setBackgroundResource(androidx.constraintlayout.widget.R.drawable.abc_edit_text_material)
        etLastName.isEnabled = true
        etLastName.setBackgroundResource(androidx.constraintlayout.widget.R.drawable.abc_edit_text_material)
        etEmail.isEnabled = true
        etEmail.setBackgroundResource(androidx.constraintlayout.widget.R.drawable.abc_edit_text_material)
        etUsername.isEnabled = true
        etUsername.setBackgroundResource(androidx.constraintlayout.widget.R.drawable.abc_edit_text_material)
        etPassword.isEnabled = true
    }
    private fun makeFormNotEditable() {
        etName.isEnabled = false
        etName.setBackgroundResource(android.R.color.transparent)
        etLastName.isEnabled = false
        etLastName.setBackgroundResource(android.R.color.transparent)
        etEmail.isEnabled = false
        etEmail.setBackgroundResource(android.R.color.transparent)
        etUsername.isEnabled = false
        etUsername.setBackgroundResource(android.R.color.transparent)
        etPassword.isEnabled = false
    }
}