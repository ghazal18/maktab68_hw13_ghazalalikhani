package com.example.myapplication.dataBase
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAccount(
    @PrimaryKey(autoGenerate = true) val ID: Int,
    val accountNumber: Int,
    val accountType: String,
    val Inventory: Int
)
