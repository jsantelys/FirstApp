package com.utn.firstapp.fragments

import android.media.Image.Plane
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.utn.firstapp.R
import com.utn.firstapp.adapters.ContactsAdapter
import com.utn.firstapp.entities.Contact
import com.utn.firstapp.entities.ContactsRepository
import com.utn.firstapp.entities.User

class Screen2Fragment : Fragment() {

    private lateinit var v : View
    private lateinit var recContact : RecyclerView
    private lateinit var btnLogout : Button

    private lateinit var adapter : ContactsAdapter

    var contactsRepository : ContactsRepository = ContactsRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        v = inflater.inflate(R.layout.fragment_screen2, container, false)
        recContact= v.findViewById(R.id.recContact)
        btnLogout = v.findViewById(R.id.btnLogout)
        return v
    }

    override fun onStart() {
        super.onStart()

        adapter = ContactsAdapter(contactsRepository.contacts){ position ->
            Snackbar.make(v, "Clickee en ${contactsRepository.contacts[position].name}", Snackbar.LENGTH_SHORT).show()
            val action = Screen2FragmentDirections.actionScreen2FragmentToContactDetailFragment(contactsRepository.contacts[position])
            findNavController().navigate(action)
        }
        recContact.layoutManager = LinearLayoutManager(context)
        recContact.adapter= adapter
        btnLogout.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}