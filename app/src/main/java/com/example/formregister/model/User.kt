package com.mtu.formregister.model

import java.util.Date

data class User(val firstName: String, val lastName: String, val sex: Boolean, val dob: Date, val address: String, val email: String){
    init {
        println("New user created!")
    }
}
