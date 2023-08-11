package com.acme.campaignproject.activity.login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.dashboard.AdminDashboardActivity
import com.acme.campaignproject.activity.dashboard.BaseActivity
import com.acme.campaignproject.activity.dashboard.ClientDashBoardActivity
import com.acme.campaignproject.activity.vender.VenderDashBoardActivity
import com.acme.campaignproject.databinding.ActivityLoginBinding
import com.acme.campaignproject.utility.AppPreferences
import com.acme.campaignproject.utility.NetworkUtils
import com.acme.campaignproject.viewmodel.LoginActivityViewModel
import com.acme.campaignproject.viewmodel.WelcomeActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {


    private lateinit var binding : ActivityLoginBinding
    var hidePassword = true
    var loginType = 1
    private lateinit var loginActivityViewModel: LoginActivityViewModel
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.etLoginid.setText(intent.extras?.getString("Email","") ?: "")

        loginActivityViewModel = ViewModelProvider(this)[LoginActivityViewModel::class.java]
        loginActivityViewModel.successresponse.observe(this, Observer {
            hideProgressDialog()
            Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
            Log.d("result","ressponse "+it)

            //add shared preferences
            AppPreferences.getInstance(this).saveUserData(it.toString())


            if (loginType==0){
                startActivity(Intent(this,ClientDashBoardActivity::class.java))
            }else if(loginType==1){
                startActivity(Intent(this,AdminDashboardActivity::class.java))
            }else{
                startActivity(Intent(this,VenderDashBoardActivity::class.java))
            }





//            startActivity(Intent(this,LoginActivity::class.java)
//                .putExtra("Email",binding.etLoginid.text.toString()))

        })

        loginActivityViewModel.errorMessage.observe(this, Observer {
            hideProgressDialog()
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            Log.d("result","ressponse "+it)
        })


    }

    fun btnloginClick(view: View){

        if (binding.etLoginid.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty()){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show()
        }else if (!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this,"Check your Internet Connection and Try Again", Toast.LENGTH_LONG).show()
        }else{

            coroutineScope.launch {

                showProgressDialog()
                loginActivityViewModel.callLogin(binding.etLoginid.text.toString(),
                    binding.etPassword.text.toString())
            }



        }

    }

    @SuppressLint("ResourceAsColor")
    fun btnAdminClick(view: View){
        loginType=1
        binding.tvAdminLogin.setBackgroundResource(R.drawable.primaryround)
        binding.tvClientLogin.setBackgroundResource(R.color.coloryellow)
        binding.tvVenderLogin.setBackgroundResource(R.color.coloryellow)

        binding.tvAdminLogin.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tvClientLogin.setTextColor(Color.parseColor("#0089BE"))
        binding.tvVenderLogin.setTextColor(Color.parseColor("#0089BE"))

    }



    @SuppressLint("ResourceAsColor")
    fun btnVenderClick(view: View){
        loginType=2
        binding.tvVenderLogin.setBackgroundResource(R.drawable.primaryround)
        binding.tvClientLogin.setBackgroundResource(R.color.coloryellow)
        binding.tvAdminLogin.setBackgroundResource(R.color.coloryellow)

        binding.tvAdminLogin.setTextColor(Color.parseColor("#0089BE"))
        binding.tvClientLogin.setTextColor(Color.parseColor("#0089BE"))
        binding.tvVenderLogin.setTextColor(Color.parseColor("#FFFFFF"))

    }






    @SuppressLint("ResourceAsColor")
    fun btnClientClick(view: View){
       loginType=0
        binding.tvAdminLogin.setBackgroundResource(R.color.coloryellow)
        binding.tvClientLogin.setBackgroundResource(R.drawable.primaryround)
        binding.tvVenderLogin.setBackgroundResource(R.color.coloryellow)

        binding.tvAdminLogin.setTextColor(Color.parseColor("#0089BE"))
        binding.tvClientLogin.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tvVenderLogin.setTextColor(Color.parseColor("#0089BE"))

    }

    fun onForgotPasswordClick(view: View){
        startActivity(Intent(this,ForgotPasswordActivity::class.java))
    }

    fun onHidePassword(view: View){
       if (hidePassword){
           hidePassword=false
           binding.ivHidepassword.setImageResource(R.drawable.ic_eye)
           binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
       }else{
           hidePassword=true
           binding.ivHidepassword.setImageResource(R.drawable.ic_eye_hide)
           binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()

       }
    }




}