package com.acme.campaignproject.activity.vender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.acme.campaignproject.R
import com.acme.campaignproject.databinding.ActivityUpdateSiteDetailBinding

class UpdateSiteDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityUpdateSiteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_update_site_detail)

    }

    fun btnCloseClick(view: View){
        this.finish()
    }

    fun btnSaveClick(view: View){
     //   this.finish()
    }

}