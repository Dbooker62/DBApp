package com.example.roomdbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.roomdbapp.database.TheDatabase
import com.example.roomdbapp.model.Contact
import com.example.roomdbapp.repository.Repo
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {


     val readAllData: LiveData<List<Contact>>
    private val repo: Repo


    init {
        val ourDao = TheDatabase.getDatabase(application).contactDao()
        repo = Repo(ourDao)
        readAllData = repo.readAllTheData
    }

    fun addUser(user: Contact){
        // run this in a background thread or worker thread
        //bad to launch database form main thread - puts too much resources on main thread
        viewModelScope.launch(Dispatchers.IO){
            repo.addContact(user)
        }
    }

    fun updateUser(user: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateContact(user)
        }
    }

    fun deleteUser(user: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteContact(user)
        }

    }
    fun deleteAllUser()
    {
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteAllUsers()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Contact>>
    {
        return repo.searchDatabase(searchQuery).asLiveData()
    }
}