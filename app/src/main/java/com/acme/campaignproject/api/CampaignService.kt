package com.acme.campaignproject.api

import com.acme.campaignproject.models.SendOtpModel
import com.acme.campaignproject.models.SendOtpResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CampaignService {


    @FormUrlEncoded
    @POST("send-otp")
    fun sendOtp(@Field("county_code") countryCode: String?,
                        @Field("mobile") mobile: String?): Call<SendOtpResponseModel>


    @FormUrlEncoded
    @POST("login-user")
    fun loginUser(@Field("loginid") countryCode: String?,
                        @Field("password") mobile: String?): Call<SendOtpResponseModel>



//    @GET("allclients")
//    fun allClients():Call<>


    companion object {
        var retrofitService: CampaignService? = null
        fun getInstance() : CampaignService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://leavecasa.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CampaignService::class.java)
            }
            return retrofitService!!
        }

    }



}