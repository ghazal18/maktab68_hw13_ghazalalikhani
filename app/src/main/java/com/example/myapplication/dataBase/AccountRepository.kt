package com.example.myapplication.dataBase

import android.content.Context
import androidx.lifecycle.LiveData

object AccountRepository {
    var dataBase: MYDataBase? = null
    var wordDao: AccountDao? = null

    fun initDB(context: Context) {
        dataBase = MYDataBase.getAppDataBase(context)
        wordDao = dataBase?.accountDao()
    }
    fun getAccounts(): LiveData<List<UserAccount>> {
        return dataBase!!.accountDao().getAll()
    }


    fun getOneAccount(acc :Int):LiveData<UserAccount>{
        return dataBase!!.accountDao().findByAccountNumber(acc)
    }

    fun addAccount(userAccount: UserAccount){
        dataBase!!.accountDao().insert(userAccount)

    }
    fun getAllAccounts():LiveData<List<UserAccount>>{
        return dataBase!!.accountDao().getAll()
    }
    fun deleteAll(){
        dataBase!!.accountDao().deleteAll()
    }
//
//    fun deleteAccounts(userAccount : UserAccount){
//        dataBase!!.accountDao().delete(userAccount)
//    }
}