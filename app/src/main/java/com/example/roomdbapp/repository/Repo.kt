package com.example.roomdbapp.repository

import androidx.lifecycle.LiveData
import com.example.roomdbapp.database.ContactDao
import com.example.roomdbapp.model.Contact
import kotlinx.coroutines.flow.Flow

class Repo (private val contactDao: ContactDao){

    val readAllTheData: LiveData<List<Contact>> = contactDao.getAllData()

    suspend fun addContact(contact: Contact)
    {
        contactDao.addContact(contact)
    }

    suspend fun deleteContact(contact: Contact)
    {
        contactDao.delContact(contact)
    }
    suspend fun updateContact(contact: Contact)
    {
        contactDao.updateContact(contact)
    }
    fun searchDatabase(searchQuery: String): Flow<List<Contact>>
    {
        return contactDao.searchDatabase(searchQuery)
    }


    suspend fun deleteAllUsers()
    {
        deleteAllUsers()
    }

}