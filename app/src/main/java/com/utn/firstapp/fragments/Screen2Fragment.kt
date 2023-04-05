package com.utn.firstapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.utn.firstapp.R

class Screen2Fragment : Fragment() {

    private lateinit var btnLogout : Button
    private lateinit var v : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        v = inflater.inflate(R.layout.fragment_screen2, container, false)
        btnLogout = v.findViewById(R.id.btnLogout)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnLogout.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}