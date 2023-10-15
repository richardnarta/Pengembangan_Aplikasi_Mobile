package com.example.tugas5.data

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginData(context:Context) {

    private val loginInfo = context.createDataStore(name = "user_info")

    companion object{
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
        val USER_PASS_KEY = preferencesKey<String>("USER_PASS")
    }

    suspend fun storeUser(name:String, pass:String){
        loginInfo.edit {
            it[USER_NAME_KEY] = name
            it[USER_PASS_KEY] = pass
        }
    }

    val userNameFlow : Flow<String> = loginInfo.data.map {
        it[USER_NAME_KEY] ?: ""
    }

    val userPassFlow : Flow<String> = loginInfo.data.map {
        it[USER_PASS_KEY] ?: ""
    }

}

class LoginCondition(context: Context){
    private val login = context.createDataStore(name = "is_login")

    companion object {
        val IS_LOGIN = preferencesKey<Boolean>("IS_LOGIN")
    }

    suspend fun storeSession(isLogin:Boolean){
        login.edit {
            it[IS_LOGIN] = isLogin
        }
    }

    val isLoginFlow : Flow<Boolean> = login.data.map {
        it[IS_LOGIN] ?: false
    }

}

class SplashCondition(context: Context){
    private val login = context.createDataStore(name = "is_open")

    companion object {
        val IS_OPEN = preferencesKey<Boolean>("IS_OPEN")
    }

    suspend fun storeSession(isOpen:Boolean){
        login.edit {
            it[IS_OPEN] = isOpen
        }
    }

    val isOpenFlow : Flow<Boolean> = login.data.map {
        it[IS_OPEN] ?: false
    }

}