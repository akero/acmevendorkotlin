package com.acme.campaignproject.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.acme.campaignproject.R
import com.acme.campaignproject.databinding.ActivityChangePasswordBinding
import com.acme.campaignproject.utility.NetworkUtils

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding:ActivityChangePasswordBinding
//asdsas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change_password)
    }

    fun btnResetClick(view: View){

        if (binding.etOldPassword.text.isNullOrEmpty()
            || binding.etNewPassword.text.isNullOrEmpty()
            || binding.etConfirmPassword.text.isNullOrEmpty() ){
            Toast.makeText(this,"Fill all the fields", Toast.LENGTH_LONG).show()
        }else if(!binding.etNewPassword.text.isNullOrEmpty().equals(binding.etConfirmPassword.text.isNullOrEmpty())){
            Toast.makeText(this,"New and Confirm Passwords must be same", Toast.LENGTH_LONG).show()
        }else{
            if (!NetworkUtils.isNetworkAvailable(this)){
                Toast.makeText(this,"Check your Internet Connection and Try Again", Toast.LENGTH_LONG).show()
            }else{

            }
        }

    }

}