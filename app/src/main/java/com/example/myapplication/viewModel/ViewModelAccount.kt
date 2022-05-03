package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.dataBase.AccountRepository
import com.example.myapplication.dataBase.UserAccount


class ViewModelAccount(app: Application) : AndroidViewModel(app) {
    init {
        AccountRepository.initDB(app.applicationContext)
    }

    fun addToREP(userAccount: UserAccount) {
        AccountRepository.addAccount(userAccount)
    }

    fun deleteAll() {
        AccountRepository.deleteAll()
    }

    fun findAccount(acc: Int): LiveData<UserAccount> {
        return AccountRepository.getOneAccount(acc)
    }

    fun getAllAccounts(): LiveData<List<UserAccount>> {
        return AccountRepository.getAllAccounts()
    }
}