package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.AccountRepository


class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    var sp: SharedPreferences
    var editor: SharedPreferences.Editor
    val nameLiveData = MutableLiveData<String>()
    val fatherNameLiveData = MutableLiveData<String>()
    val phoneNumberLiveData = MutableLiveData<String>()
    val postCodeLiveData = MutableLiveData<String>()

    init {
        sp = app.applicationContext.getSharedPreferences("userInformation", Context.MODE_PRIVATE)
        editor = sp.edit()
        AccountRepository.initDB(app.applicationContext)
    }

    fun sandInformationToEditor() {
        editor.putString("name", nameLiveData.value.toString())
        editor.putString("fathersName", fatherNameLiveData.value.toString())
        editor.putString("phone", phoneNumberLiveData.value.toString())
        editor.putString("postCode", postCodeLiveData.value.toString())
//        editor.putInt("AccountNumber", editTextAccount.value.toString().toInt())
        editor.apply()
    }


}