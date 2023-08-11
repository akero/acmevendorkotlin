package com.acme.campaignproject.repository

import android.telecom.Call
import androidx.lifecycle.MutableLiveData
import com.acme.campaignproject.api.CampaignService
import com.acme.campaignproject.models.SendOtpModel
import com.acme.campaignproject.models.SendOtpResponseModel
import retrofit2.Callback
import retrofit2.Response

class CampaignRepository(private val campaignService: CampaignService) {


    private val sendotpLiveData = MutableLiveData<SendOtpResponseModel>()
//    val quotes:LiveData<QuoteList>
//    get() = quoteLiveData


      suspend fun sendOtp(countrycode:String,mobile:String){
        val result = campaignService.sendOtp(countrycode,mobile).enqueue(object: Callback<SendOtpResponseModel> {
            override fun onResponse(
                call: retrofit2.Call<SendOtpResponseModel>,
                response: Response<SendOtpResponseModel>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: retrofit2.Call<SendOtpResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

          return result
    }








}