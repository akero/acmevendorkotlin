package com.acme.campaignproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acme.campaignproject.api.CampaignService
import com.acme.campaignproject.models.SendOtpModel
import com.acme.campaignproject.models.SendOtpResponseModel
import com.acme.campaignproject.repository.CampaignRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel:ViewModel() {

     val successresponse = MutableLiveData<SendOtpResponseModel>()
     val errorMessage = MutableLiveData<String>()

     fun callLogin(countrycode:String, mobile:String) {

         CampaignService.getInstance().loginUser(countrycode, mobile).enqueue(object :
             Callback<SendOtpResponseModel> {
             override fun onResponse(
                 call: Call<SendOtpResponseModel>,
                 response: Response<SendOtpResponseModel>
             ) {
                 if (response.isSuccessful) {
                     successresponse.value = response.body()
                 } else {
                     errorMessage.value = response.message()
                 }
             }

             override fun onFailure(call: Call<SendOtpResponseModel>, t: Throwable) {
                 errorMessage.value = t.toString()
             }

         })

     }
}