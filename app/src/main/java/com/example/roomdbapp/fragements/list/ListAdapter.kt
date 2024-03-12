package com.example.roomdbapp.fragements.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbapp.R
import com.example.roomdbapp.model.Contact


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var contactList = emptyList<Contact>()

    // Define the ViewHolder for the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idText: TextView = itemView.findViewById(R.id.id_txt)
        val fname: TextView = itemView.findViewById(R.id.fname_rc)
        val lname: TextView = itemView.findViewById(R.id.lname_rc)
        val email: TextView = itemView.findViewById(R.id.email_rc)
        val phone: TextView = itemView.findViewById(R.id.phone_rc)
        val city: TextView = itemView.findViewById(R.id.city_rc)
        val state: TextView = itemView.findViewById(R.id.state_rc)


        val rowLayout: View = itemView.findViewById(R.id.rowLayout)

    }

    // Inflate the item layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(itemView)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.idText.text = currentItem.ID.toString()
        holder.fname.text = currentItem.firstName.toString()
        holder.lname.text = currentItem.lastName.toString()
        holder.email.text = currentItem.email.toString()
        holder.phone.text = currentItem.phone.toString()
        holder.city.text = currentItem.address.city.toString()
        holder.state.text = currentItem.address.state.toString()

        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    // Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return contactList.size
    }


    fun setData(newContactList: List<Contact>) {
        this.contactList = newContactList
        notifyDataSetChanged()
    }
}