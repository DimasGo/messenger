package com.example.telegram.model

data class User(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var status: String = "",
    var photo: String = "",
    var phone: String = ""
)