package com.utn.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.utn.firstapp.R

class DeleteContactPopUpFragment(
    private val onSubmitClickListener: () -> Unit
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delete_contact_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDelete = view.findViewById<Button>(R.id.btnConfirmDeleteContact)
        val btnCancel = view.findViewById<Button>(R.id.btnCancelDeleteContact)

        btnDelete.setOnClickListener {
            onSubmitClickListener.invoke()
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }
}