package com.example.roomdbapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    @ColumnInfo(name ="fname" ) val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,

    @Embedded
    val address: Address


) :  Parcelable

@Parcelize
data class Address(
    val city: String,
    val state: String
        ): Parcelable


