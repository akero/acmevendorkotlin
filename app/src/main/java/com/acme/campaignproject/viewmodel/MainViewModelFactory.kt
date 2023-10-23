package com.acme.campaignproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acme.campaignproject.repository.CampaignRepository
//dasdsa
class MainViewModelFactory(private val repository: CampaignRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        }
//        else  if (modelClass.isAssignableFrom(WelcomeActivityViewModel::class.java)) {
//            WelcomeActivityViewModel(this.repository) as T
//        }
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}