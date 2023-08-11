package com.acme.campaignproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acme.campaignproject.models.SendOtpModel
import com.acme.campaignproject.repository.CampaignRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CampaignRepository):ViewModel() {
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getQuotes(1)
//        }
//    }
//        val quotes:LiveData<QuoteList>
//        get() = repository.quotes


    init{
      //  callOtp(sendOtpModel = SendOtpModel())
    }

//     fun callOtp(sendOtpModel: SendOtpModel) {
//         viewModelScope.launch {
//             try{
//                 val otpResponse = repository.sendOtp(sendOtpModel)
//
//             }catch (e:Exception){
//
//             }
//         }
//    }


}