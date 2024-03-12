package com.example.roomdbapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdbapp.model.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact : Contact)


    @Delete
    suspend fun delContact(contact: Contact)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAllUsers()

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("Select * from contact_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Contact>>


     @Query("SELECT * FROM contact_table WHERE fname LIKE :searchQuery OR lastname LIKE :searchQuery ")
     fun searchDatabase(searchQuery: String): Flow<List<Contact>>


}