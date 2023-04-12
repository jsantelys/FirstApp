package com.utn.firstapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.firstapp.R
import com.utn.firstapp.entities.Contact

class ContactsAdapter (
    var contactList: MutableList<Contact>,
    var onClick : (Int) -> Unit
        ) : RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

            class ContactHolder (v: View) : RecyclerView.ViewHolder(v) {
                private var view : View

                init {
                    this.view = v
                }
                fun setFullName (fullName: String) {
                    var txtFullName: TextView = view.findViewById(R.id.txtFullName)
                    txtFullName.text = fullName
                }
                fun setPhoneNumber (phoneNumber: String) {
                    var txtPhoneNumber : TextView = view.findViewById(R.id.txtPhoneNumber)
                    txtPhoneNumber.text = phoneNumber
                }
                fun getCard() : CardView {
                    return view.findViewById(R.id.cardContact)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout,parent,false)
        return (ContactHolder(view))
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.setFullName(contactList[position].getFullName())
        holder.setPhoneNumber(contactList[position].phoneNumber)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}