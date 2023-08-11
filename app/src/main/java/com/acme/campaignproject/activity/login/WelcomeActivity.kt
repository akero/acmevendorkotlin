package com.acme.campaignproject.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.dashboard.AdminDashboardActivity
import com.acme.campaignproject.activity.dashboard.BaseActivity
import com.acme.campaignproject.api.CampaignService
import com.acme.campaignproject.api.RetroiftHelper
import com.acme.campaignproject.databinding.ActivityForgotPasswordBinding
import com.acme.campaignproject.databinding.ActivityWelcomeBinding
import com.acme.campaignproject.models.SendOtpModel
import com.acme.campaignproject.models.SendOtpResponseModel
import com.acme.campaignproject.repository.CampaignRepository
import com.acme.campaignproject.utility.NetworkUtils
import com.acme.campaignproject.viewmodel.MainViewModelFactory
import com.acme.campaignproject.viewmodel.WelcomeActivityViewModel
import com.google.gson.Gson
import kotlinx.coroutines.*

class WelcomeActivity : BaseActivity() {

    lateinit var binding: ActivityWelcomeBinding
    private lateinit var welcomeActivityViewModel: WelcomeActivityViewModel
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)

        welcomeActivityViewModel = ViewModelProvider(this)[WelcomeActivityViewModel::class.java]
        welcomeActivityViewModel.successresponse.observe(this, Observer {
           hideProgressDialog()
           Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
           Log.d("result","ressponse "+it)

           startActivity(Intent(this,LoginActivity::class.java)
               .putExtra("Email",binding.etEmailId.text.toString()))

       })

     welcomeActivityViewModel.errorMessage.observe(this, Observer {
            hideProgressDialog()
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            Log.d("result","ressponse "+it)
       })


    }


    fun btnSubmitClick(view: View){

        if (binding.etEmailId.text.isNullOrEmpty()){
            Toast.makeText(this,"Fill the Email", Toast.LENGTH_LONG).show()
        }

        else  if (!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this,"Check your Internet Connection and Try Again", Toast.LENGTH_LONG).show()
        }else{

            coroutineScope.launch {

                   showProgressDialog()
                    welcomeActivityViewModel.callOtp("+91",binding.etEmailId.text.toString())
                }

            }

    }






}

