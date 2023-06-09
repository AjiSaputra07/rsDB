package com.example.pasienrumahsakit.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noKTP: String,
    val nama: String,
    val alamat: String,
    val noHP: Int
): Parcelable