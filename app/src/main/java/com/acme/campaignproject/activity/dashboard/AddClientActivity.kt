package com.acme.campaignproject.activity.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.acme.campaignproject.R
import com.acme.campaignproject.databinding.ActivityAddClientBinding
import com.acme.campaignproject.utility.NetworkUtils

class AddClientActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_client)
    }


    fun btnCloseClick(view: View){
      this.finish()
    }

    fun btnSaveClick(view: View){
        if (binding.etFullName.text.isNullOrEmpty()
            || binding.etEmail.text.isNullOrEmpty()
            || binding.etCompanyName.text.isNullOrEmpty()
            || binding.etCompanyAddress.text.isNullOrEmpty()
            || binding.etGst.text.isNullOrEmpty()
            || binding.etPhone.text.isNullOrEmpty() ){
            Toast.makeText(this,"Fill all the fields", Toast.LENGTH_LONG).show()
        }else if (!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this,"Check your Internet Connection and Try Again", Toast.LENGTH_LONG).show()
        }else{

            binding.etFullName.setText("")
            binding.etEmail.setText("")
            binding.etCompanyName.setText("")
            binding.etCompanyAddress.setText("")
            binding.etGst.setText("")
            binding.etPhone.setText("")
            showSuccessMessage()
        }
    }


    fun showSuccessMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.custom_emailsent, null)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        val tvResubmit = view.findViewById<TextView>(R.id.tvResubmit)
        tvResubmit.visibility = View.GONE
        tvMsg.text =  "Client Added Successfully"
        val btnClose = view.findViewById<Button>(R.id.btnClose)
        builder.setView(view)
        val dialog = builder.create()
        if (dialog != null) {
            dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        }
        btnClose.setOnClickListener {
            if (dialog != null) {
                dialog.dismiss()
              //  this.finish()
            }
        }
        if (dialog != null) {
            dialog.show()
        }
    }


}