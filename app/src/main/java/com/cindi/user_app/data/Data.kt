package com.cindi.user_app.data


import com.google.gson.annotations.SerializedName

data class Data(
    var avatar: String?,
    var email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    var id: Int?,
    @SerializedName("last_name")
    var lastName: String?
)