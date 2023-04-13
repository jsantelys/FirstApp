package com.utn.firstapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.utn.firstapp.R

class ContactDetailFragment : Fragment() {

    private lateinit var v : View
    private lateinit var txtName : TextView
    private lateinit var txtLastName : TextView
    private lateinit var txtPhone : TextView
    private lateinit var txtDate : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_contact_detail, container, false)
        txtName = v.findViewById(R.id.txtName)
        txtLastName = v.findViewById(R.id.txtLastName)
        txtPhone = v.findViewById(R.id.txtPhone)
        txtDate = v.findViewById(R.id.txtDate)
        return v
    }

    override fun onStart() {
        super.onStart()

        val arg = ContactDetailFragmentArgs.fromBundle(requireArguments()).contactInfo
        txtName.text = arg.name
        txtLastName.text = arg.lastName
        txtPhone.text = arg.phoneNumber
        txtDate.text = arg.dateAdded
    }
}