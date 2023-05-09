package com.utn.firstapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.utn.firstapp.R
import com.utn.firstapp.database.AppDatabase
import com.utn.firstapp.database.ContactDao
import com.utn.firstapp.entities.Contact

class ContactDetailFragment : Fragment() {

    private var db: AppDatabase? = null
    private var contactDao: ContactDao? = null

    private lateinit var v : View
    private lateinit var txtName : TextView
    private lateinit var txtLastName : TextView
    private lateinit var txtPhone : TextView
    private lateinit var txtDate : TextView
    private lateinit var txtNumberOfCalls : TextView
    private lateinit var txtNumberOfMessages : TextView

    private lateinit var contact: Contact

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_contact_detail, container, false)
        txtName = v.findViewById(R.id.txtName)
        txtLastName = v.findViewById(R.id.txtLastName)
        txtPhone = v.findViewById(R.id.txtPhone)
        txtDate = v.findViewById(R.id.txtDate)
        txtNumberOfCalls = v.findViewById(R.id.txtCalls)
        txtNumberOfMessages = v.findViewById(R.id.txtMessages)
        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(requireContext())
        contactDao = db?.contactDao()

        val userId = requireActivity().intent.extras!!.getInt("user_id")

        val id = ContactDetailFragmentArgs.fromBundle(requireArguments()).id
        contact = contactDao?.getContactById(userId.toString(),id) as Contact

        txtName.text = contact.name
        txtLastName.text = contact.lastName
        txtPhone.text = contact.phoneNumber
        txtDate.text = contact.dateAdded
        txtNumberOfCalls.text = contact.numberOfCalls.toString()
        txtNumberOfMessages.text = contact.numberOfMessages.toString()
    }
}