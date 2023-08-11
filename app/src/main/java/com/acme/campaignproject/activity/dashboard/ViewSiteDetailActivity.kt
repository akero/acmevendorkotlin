package com.acme.campaignproject.activity.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.acme.campaignproject.R
import com.acme.campaignproject.databinding.ActivityViewSiteDetailBinding

class ViewSiteDetailActivity : AppCompatActivity() {

    var campaignType = ""
    var position:Int = 0
    lateinit var binding: ActivityViewSiteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_site_detail)

        campaignType = intent.extras?.getString("campaignType","") ?: ""
        position = intent.extras?.getInt("position",0) ?: 0

        if (campaignType.equals("old",ignoreCase = true)){
            binding.tvOldCampaign.performClick()
        }else{
            binding.tvLiveCampaign.performClick()
        }


    }

    fun btnCloseClick(view: View){
        this.finish()
    }

    fun btnNextClick(view: View){
     //   this.finish()
    }


    fun onDownloadClick(view: View){
      //  this.finish()
    }

    fun oldCampaignClick(view: View){
        binding.tvOldCampaign.setBackgroundResource(R.drawable.primaryround)
        binding.tvLiveCampaign.setBackgroundResource(R.color.coloryellow)
    }
    fun liveCampaignClick(view: View){
        binding.tvLiveCampaign.setBackgroundResource(R.drawable.primaryround)
        binding.tvOldCampaign.setBackgroundResource(R.color.coloryellow)
    }


}