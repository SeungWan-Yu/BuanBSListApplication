package com.buan.buanbslistapplication

import kotlinx.serialization.Serializable

@Serializable
data class Users(
    val name: String,
    val phone: String,
    val address: String,
    val area: Int,
    val belong: String,
    val id: Int,
    val photo : String,
    val etc : String
)

@Serializable
data class Version(
    val vs : String
)
