package com.example.roomdbapp.fragements.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdbapp.R
import com.example.roomdbapp.model.Address
import com.example.roomdbapp.model.Contact
import com.example.roomdbapp.viewmodel.ContactViewModel


class AddFragment : Fragment() {

    private lateinit var CVM: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

            CVM = ViewModelProvider(this).get(ContactViewModel::class.java)

        view.findViewById<Button>(R.id.add_btn).setOnClickListener {
            insertDataToDatabase(view)
        }




        return view
    }

    private fun insertDataToDatabase(view: View)
    {
        val fname = view.findViewById<EditText>(R.id.fname).text.toString()
        val lname = view.findViewById<EditText>(R.id.lname).text.toString()
        val email = view.findViewById<EditText>(R.id.email).text.toString()
        val phone = view.findViewById<EditText>(R.id.phone).text.toString()
        val city = view.findViewById<EditText>(R.id.city).text.toString()
        val state = view.findViewById<EditText>(R.id.state).text.toString()


        if(inputCheck(fname, lname , email , phone ))
        {
            val address = Address(city , state)

            val contact = Contact(0 , fname , lname , email , phone , address )
            CVM.addUser(contact)
            Toast.makeText(requireContext(), "Successfully inserted Contact " , Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else
        {
            Toast.makeText(requireContext(), "PLease insert all fields " , Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(fname: String , lname: String , email: String , phone: String): Boolean
    {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone))
    }



}