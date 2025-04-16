package com.example.littlelemon

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
){

     fun isUserValid(): Boolean {
        return !(firstName.isBlank() || lastName.isBlank() || email.isBlank())
    }
}


