package com.example.roomdbapp.fragements.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdbapp.R
import com.example.roomdbapp.model.Address
import com.example.roomdbapp.model.Contact
import com.example.roomdbapp.viewmodel.ContactViewModel


class UpdateFragment : Fragment() {

    private lateinit var CVM : ContactViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        CVM = ViewModelProvider(this).get(ContactViewModel::class.java)



        val fname = view.findViewById<EditText>(R.id.ufname)
        val lname = view.findViewById<EditText>(R.id.ulname)
        val email = view.findViewById<EditText>(R.id.uemail)
        val phone = view.findViewById<EditText>(R.id.uphone)
        val city = view.findViewById<EditText>(R.id.ucity)
        val state = view.findViewById<EditText>(R.id.ustate)
        val button = view.findViewById<Button>(R.id.update_btn)
        val delete = view.findViewById<Button>(R.id.delete_btn)

        fname.setText(args.currentUser.firstName)
        lname.setText(args.currentUser.lastName)
        email.setText(args.currentUser.email)
        phone.setText(args.currentUser.phone)

        button.setOnClickListener {
            updateItem(view)
        }
        delete.setOnClickListener {
            deleteContact()
        }


//        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(view: View)
    {
        val fname = view.findViewById<EditText>(R.id.ufname).text.toString()
        val lname = view.findViewById<EditText>(R.id.ulname).text.toString()
        val email = view.findViewById<EditText>(R.id.uemail).text.toString()
        val phone = view.findViewById<EditText>(R.id.uphone).text.toString()
        val city = view.findViewById<EditText>(R.id.ucity).text.toString()
        val state = view.findViewById<EditText>(R.id.ustate).text.toString()

        if(inputCheck(fname, lname , email , phone))
        {
            val address = Address(city , state)


            val updateUser = Contact(args.currentUser.ID , fname , lname , email, phone , address)
            CVM.updateUser(updateUser)
            Toast.makeText(requireContext(), "Contacts inserted Successfully " , Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if(item.itemId == R.id.menu_delete)
//        {
//            deleteContact()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun deleteContact()
    {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            CVM.deleteUser(args.currentUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully removed ${args.currentUser.firstName} " , Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No"){
            _, _ ->
        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }
}