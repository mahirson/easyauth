package com.mn.easyauth

import android.content.Context
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

class EasyAuth<T : User?> {
    /*
     * get current user
     */
    /*
           * this is your authenticated user class
           * must be extended User class
            */
    var user: T? = null
        private set
    private val TAG = javaClass.name

    /*
     * this is your authenticated user class type
     * must be extended User class
     * type is using for Json Processing
     */
    private val type: Class<T>
    private val context: Context
    private val printer: EasyAuthPrinter

    /*
     * normal constructor class
     */
    constructor(context: Context, type: Class<T>) {
        this.type = type
        this.context = context
        EasyAuthLog.logType = LogType.ALL
        printer = EasyAuthPrinter()
    }

    /*
     * type is your authenticated user class type
     * logType is your logging level. Can be Debug,Release and All
     */
    constructor(context: Context, type: Class<T>, logType: LogType?) {
        this.type = type
        this.context = context
        EasyAuthLog.logType = logType
        printer = EasyAuthPrinter()
    }

    private fun deleteUser() {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    /*
     * saving user to shared preferences
     */
    fun saveUser(user: T) {
        val ow = ObjectMapper().writer().withDefaultPrettyPrinter()
        var json: String? = null
        try {
            json = ow.writeValueAsString(user)
            saveUser(json)
            setUser(user)
            printer.print(TAG, "user saved $json")
        } catch (e: JsonProcessingException) {
            printer.print(TAG, e.message)
        }
    }

    fun saveUser(user: String?) {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("data", user)
        editor.apply()
    }

    /*
     * check if user exists or not
     */
    fun hasUser(): Boolean {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val user: T
        try {
            user = ObjectMapper().readValue(preferences.getString("data", ""), type)
            setUser(user)
            printer.print(TAG, "has saved user$user")
            return true
        } catch (e: JsonProcessingException) {
            printer.print(TAG, "there is no user")
        }
        return false
    }

    /*
     * logs out
     */
    fun logOut() {
        deleteUser()
    }

    private fun setUser(user: T) {
        this.user = user
    }
}