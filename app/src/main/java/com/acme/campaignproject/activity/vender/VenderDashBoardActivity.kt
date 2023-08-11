package com.acme.campaignproject.activity.vender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.dashboard.ViewSiteDetailActivity
import com.acme.campaignproject.adapters.CampaignListAdapter
import com.acme.campaignproject.databinding.ActivityVenderDashBoardBinding
import org.json.JSONArray
import org.json.JSONObject

class VenderDashBoardActivity : AppCompatActivity() {

    lateinit var binding:ActivityVenderDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_vender_dash_board)
        campaignList()
    }


    private fun campaignList() {
        val layoutManager = GridLayoutManager(this, 1)
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
            Intent(this, UpdateSiteDetailActivity::class.java)
        )
    }


}