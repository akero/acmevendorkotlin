package com.acme.campaignproject.activity.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.dashboard.ClientDashBoardActivity
import com.acme.campaignproject.databinding.ActivityForgotPasswordBinding
import com.acme.campaignproject.utility.NetworkUtils

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var binding:ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password)
    }


    fun btnSubmitClick(view: View){

        if (binding.etEmailId.text.isNullOrEmpty()){
            Toast.makeText(this,"Fill the Email", Toast.LENGTH_LONG).show()
        }

        else  if (!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this,"Check your Internet Connection and Try Again", Toast.LENGTH_LONG).show()
        }else{
            showSuccessMessage()
        }


    }

    fun btnSigninClick(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }


    fun showSuccessMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.custom_emailsent, null)
        val tvResubmit = view.findViewById<TextView>(R.id.tvResubmit)
        var text = "<font color=#0089BE>Can't get email? </font> <font color=#DA2829>Resubmit</font>"
        tvResubmit.text = Html.fromHtml(text)
        val btnClose = view.findViewById<Button>(R.id.btnClose)
        builder.setView(view)
        val dialog = builder.create()
        if (dialog != null) {
            dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        }
        btnClose.setOnClickListener {
            if (dialog != null) {
                dialog.dismiss()
                this.finish()
            }
        }
        if (dialog != null) {
            dialog.show()
        }
    }





}