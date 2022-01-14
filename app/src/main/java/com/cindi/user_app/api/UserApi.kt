package com.cindi.user_app.api

import com.cindi.user_app.data.user_data
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users?page=1&per_page=10")
    fun getSelectedName() : Call<user_data>
    @GET("users?page=2&per_page=10")
    fun getSelectedNext() : Call<user_data>

}
