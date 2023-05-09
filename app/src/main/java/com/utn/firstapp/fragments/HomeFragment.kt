package com.utn.firstapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.utn.firstapp.R
import com.utn.firstapp.adapters.ContactsAdapter
import com.utn.firstapp.database.AppDatabase
import com.utn.firstapp.database.ContactDao
import com.utn.firstapp.entities.Contact

class HomeFragment : Fragment() {

    private var db: AppDatabase? = null
    private var contactDao: ContactDao? = null

    private lateinit var v : View
    private lateinit var recContact : RecyclerView
    private lateinit var fabAddContact: FloatingActionButton

    private lateinit var adapter : ContactsAdapter

    private lateinit var contactsList : MutableList<Contact>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        recContact= v.findViewById(R.id.recContact)
        fabAddContact = v.findViewById(R.id.fabAddContact)
        return v
    }


    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(requireContext())
        contactDao = db?.contactDao()

        val userId = requireActivity().intent.extras!!.getInt("user_id")

        contactsList = contactDao?.getContactsOrderedByName(userId.toString()) as MutableList<Contact>

        adapter = ContactsAdapter(contactsList, this::onClick, this::onDelete)
        recContact.layoutManager = LinearLayoutManager(context)
        recContact.adapter= adapter

        fabAddContact.setOnClickListener{
            AddContactPopUpFragment(this::updateList).show(requireActivity().supportFragmentManager,"showAddContact")
        }
    }
    private fun onClick (position: Int){
        val action = HomeFragmentDirections.actionHomeFragmentToContactDetailFragment(contactsList[position].id)
        findNavController().navigate(action)
    }
    private fun onDelete (position: Int){
        DeleteContactPopUpFragment( onSubmitClickListener = {
            contactDao?.deleteContact(contactsList[position])
            Toast.makeText(requireContext(),"Deleted contact successfully", Toast.LENGTH_SHORT)
            val userId = requireActivity().intent.extras!!.getInt("user_id")
            contactsList = contactDao?.getContactsOrderedByName(userId.toString()) as MutableList<Contact>
            adapter = ContactsAdapter(contactsList, this::onClick, this::onDelete)
            recContact.layoutManager = LinearLayoutManager(context)
            recContact.adapter= adapter
        }).show(parentFragmentManager, "Confirm delete")

    }

    private fun updateList() {
        val userId = requireActivity().intent.extras!!.getInt("user_id")
        contactsList = contactDao?.getContactsOrderedByName(userId.toString()) as MutableList<Contact>
        adapter = ContactsAdapter(contactsList, this::onClick, this::onDelete)
        recContact.layoutManager = LinearLayoutManager(context)
        recContact.adapter= adapter
    }
}