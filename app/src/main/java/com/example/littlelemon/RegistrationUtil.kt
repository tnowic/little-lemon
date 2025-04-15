package com.example.littlelemon

import android.content.Context
import androidx.core.content.edit


object RegistrationUtil {

    private const val REGISTERED_USER = "registeredUser"
    private const val FIST_NAME = "firstname"
    private const val LAST_NAME = "lastName"
    private const val EMAIL = "email"

    fun getRegisteredUser(context: Context): User {
        val sharedPreferences =
            context.getSharedPreferences(REGISTERED_USER, Context.MODE_PRIVATE)
        return User(
            sharedPreferences.getString(FIST_NAME, "").orEmpty(),
            sharedPreferences.getString(LAST_NAME, "").orEmpty(),
            sharedPreferences.getString(EMAIL, "").orEmpty()
        )
    }

    fun registerUser(context: Context, user: User) {
        if (user.isUserValid()) {
            val sharedPreferences =
                context.getSharedPreferences(REGISTERED_USER, Context.MODE_PRIVATE)

            sharedPreferences.edit(commit = true) {
                putString(FIST_NAME, user.firstName.trim())
                putString(LAST_NAME, user.lastName.trim())
                putString(EMAIL, user.email.trim())
            }
        } else {
            throw IllegalArgumentException()
        }
    }

    fun removeRegisteredUser(context: Context) {
        context.getSharedPreferences(REGISTERED_USER, Context.MODE_PRIVATE).edit(commit = true) {
            clear()
        }
    }
}

