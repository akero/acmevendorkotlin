package com.acme.campaignproject.utility

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class AppPreferences private constructor() {

    companion object {
        private val sharePref = AppPreferences()
        private lateinit var sharedPreferences: SharedPreferences

        private val PLACE_OBJ = "place_obj"
        private val user_data = "user_data"

        fun getInstance(context: Context): AppPreferences {
            if (!::sharedPreferences.isInitialized) {
                synchronized(AppPreferences::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    val user_data: String
        get() = sharedPreferences.getString(user_data, "").toString()


    fun saveUserData(userDataStr: String) {
        sharedPreferences.edit()
            .putString(user_data, userDataStr)
            .apply()
    }

    fun removeUserData() {
        sharedPreferences.edit().remove(user_data).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}