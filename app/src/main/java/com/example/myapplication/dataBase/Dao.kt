package com.example.myapplication.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDao {
    @Query("SELECT COUNT(*) FROM UserAccount")
    fun getCount(): LiveData<Int>

    @Query("SELECT * FROM UserAccount")
    fun getAll(): LiveData<List<UserAccount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: UserAccount)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg userAccount: UserAccount)

    @Query("DELETE FROM UserAccount")
    fun deleteAll()

    @Query("SELECT * FROM UserAccount WHERE accountNumber = :accountNumber")
    fun findByAccountNumber(accountNumber: Int): LiveData<UserAccount>


}

