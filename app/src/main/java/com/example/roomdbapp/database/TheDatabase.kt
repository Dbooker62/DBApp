package com.example.roomdbapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdbapp.model.Contact


@Database(entities = [Contact::class] , version = 1 , exportSchema = false)
abstract class TheDatabase : RoomDatabase() {


    abstract fun contactDao(): ContactDao

    companion object{

        @Volatile
        private var OneInstance: TheDatabase? = null

        fun getDatabase(context: Context): TheDatabase{

            val testInstance = OneInstance
            if(testInstance != null)
            {
                return testInstance
            }
            synchronized(this)
            {
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    TheDatabase::class.java,
                    "contact_database"
                ).build()
                OneInstance = instance
                return instance
            }
        }

    }
}