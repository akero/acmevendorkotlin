package com.acme.campaignproject.activity.dashboard

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.acme.campaignproject.R
import com.acme.campaignproject.adapters.CampaignListAdapter
import com.acme.campaignproject.databinding.ActivityClientDashBoardBinding
import org.json.JSONArray
import org.json.JSONObject

class ClientDashBoardActivity : AppCompatActivity() {

    lateinit var binding:ActivityClientDashBoardBinding
    var oldcampaign = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_client_dash_board)

        campaignList()

    }


    fun oldCampaignClick(view: View){
        oldcampaign = true
        binding.tvOldCampaign.setBackgroundResource(R.drawable.primaryround)
        binding.tvLiveCampaign.setBackgroundResource(R.color.coloryellow)

        binding.tvOldCampaign.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tvLiveCampaign.setTextColor(Color.parseColor("#0089BE"))



    }
      fun liveCampaignClick(view: View){
          oldcampaign = false
          binding.tvLiveCampaign.setBackgroundResource(R.drawable.primaryround)
          binding.tvOldCampaign.setBackgroundResource(R.color.coloryellow)

          binding.tvLiveCampaign.setTextColor(Color.parseColor("#FFFFFF"))
          binding.tvOldCampaign.setTextColor(Color.parseColor("#0089BE"))


      }


    private fun campaignList() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvCampaignList.setLayoutManager(layoutManager)

        var jsonArray = JSONArray()
        var jsonObjectairbnb = JSONObject()
        var jsonObjecthyundai = JSONObject()
        var jsonObjectford = JSONObject()
        var jsonObjectpatanjli = JSONObject()


        jsonObjectairbnb.put("sitenumber","001")
        jsonObjecthyundai.put("sitenumber","002")
        jsonObjectford.put("sitenumber","003")
        jsonObjectpatanjli.put("sitenumber","004")

        jsonObjectairbnb.put("unitnumber","#887001")
        jsonObjecthyundai.put("unitnumber","#878002")
        jsonObjectford.put("unitnumber","#765003")
        jsonObjectpatanjli.put("unitnumber","#432004")



        jsonArray.put(jsonObjectairbnb)
        jsonArray.put(jsonObjecthyundai)
        jsonArray.put(jsonObjectford)
        jsonArray.put(jsonObjectpatanjli)


        val adapter = CampaignListAdapter(this, jsonArray)
        binding.rvCampaignList.adapter = adapter

    }

    fun onItemClick(position: Int) {
        startActivity(
            Intent(this,ViewSiteDetailActivity::class.java)
            .putExtra("position",position)
            .putExtra("campaignType","old")
        )
    }

}