package com.acme.campaignproject.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroiftHelper {

    private val BASE_URL = "https://leavecasa.com/api/"

    fun getInstance() :Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}