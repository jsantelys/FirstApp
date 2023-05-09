package com.utn.firstapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.utn.firstapp.R
import com.utn.firstapp.activities.AppActivity
import com.utn.firstapp.database.AppDatabase
import com.utn.firstapp.database.ContactDao
import com.utn.firstapp.entities.Contact
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddContactPopUpFragment(
    private val onSubmitClickListener: () -> Unit
) : DialogFragment() {

    private var db: AppDatabase? = null
    private var contactDao: ContactDao? = null

    private lateinit var v: View

    private lateinit var etLayoutAddName : TextInputLayout
    private lateinit var etLayoutAddLastName : TextInputLayout
    private lateinit var etLayoutPhoneNumber : TextInputLayout

    private lateinit var etAddName: TextInputEditText
    private lateinit var etAddLastName: TextInputEditText
    private lateinit var etAddPhoneNumber: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db=AppDatabase.getInstance(requireContext())
        contactDao = db?.contactDao()

        v = inflater.inflate(R.layout.fragment_add_contact_pop_up, container, false)
        etLayoutAddName = v.findViewById(R.id.etLayoutAddContactName)
        etLayoutAddLastName = v.findViewById(R.id.etLayoutAddContactLastName)
        etLayoutPhoneNumber = v.findViewById(R.id.etLayoutAddContactPhoneNumber)
        etAddName = v.findViewById(R.id.etAddContactName)
        etAddLastName = v.findViewById(R.id.etAddContactLastName)
        etAddPhoneNumber = v.findViewById(R.id.etAddContactPhone)
        return v
    }
    private fun validForm(): Boolean {
        var valid = true
        if(etAddName.text.toString().isBlank())
        {
            etLayoutAddName.error = "Can't add contact without name"
            valid = false
        }
        if(etAddPhoneNumber.text.toString().isBlank())
        {
            etLayoutPhoneNumber.error= "Can't add contact without phone number"
            valid = false
        }
        return valid
    }

    private fun contactExistsWithAddedPhone(): Boolean {
        val userId = requireActivity().intent.extras!!.getInt("user_id")
        return (contactDao?.getContactByPhoneNumber(userId.toString(),etAddPhoneNumber.text.toString())!=null)
    }

    private fun addContact(name: String, lastName: String, phoneNumber: String) {
        val todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val userId = requireActivity().intent.extras!!.getInt("user_id")

        contactDao?.insertContact(Contact(0, userId
            ,name,lastName, phoneNumber,todayDate,0,0))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddContact = view.findViewById<Button>(R.id.btnAddContactDialog)
        val btnCancelAddContact = view.findViewById<Button>(R.id.btnCancelAddContactDialog)

        btnAddContact.setOnClickListener {
            if(validForm())
            {
                if(!contactExistsWithAddedPhone())
                {
                    addContact(etAddName.text.toString(),etAddLastName.text.toString(),etAddPhoneNumber.text.toString())
                    Toast.makeText(requireContext(),"Contact added successfully",Toast.LENGTH_SHORT).show()
                    onSubmitClickListener.invoke()
                    dismiss()
                }
                else {
                    etLayoutPhoneNumber.error= "You already have a contact with this phone Number"
                }
            }
        }
        btnCancelAddContact.setOnClickListener {
            dismiss()
        }
        etAddName.addTextChangedListener {
            etLayoutAddName.error = null
        }
        etAddPhoneNumber.addTextChangedListener {
            etLayoutPhoneNumber.error = null
        }

    }
}