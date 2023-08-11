package com.acme.campaignproject.activity.dashboard

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.vender.UpdateSiteDetailActivity
import com.acme.campaignproject.adapters.CampaignListAdapter
import com.acme.campaignproject.databinding.ActivityCampaignListBinding
import org.json.JSONArray
import org.json.JSONObject

class CampaignListActivity : AppCompatActivity() {

    lateinit var binding: ActivityCampaignListBinding
    var oldcampaign = true
    var showMenus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_campaign_list)

        campaignList()

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


        val adapter = CampaignListAdapter(this,jsonArray)
        binding.rvCampaignList.adapter = adapter

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

    fun onPlusClick(view: View){

        if (showMenus){
            binding.ivPlus.setImageResource(R.drawable.ic_add)
            showMenus=false

            binding.ivAddSite.visibility = View.INVISIBLE
            binding.ivRedo.visibility = View.INVISIBLE
            binding.ivDeleteSite.visibility = View.INVISIBLE
            val animate = TranslateAnimation(
                0F, 0F, 0F,
                view.height.toFloat()
            )
            animate.duration = 0
            binding.ivAddSite.startAnimation(animate)
            binding.ivRedo.startAnimation(animate)
            binding.ivDeleteSite.startAnimation(animate)


        }else{
            showMenus=true

            binding.ivAddSite.visibility = View.VISIBLE
            binding.ivRedo.visibility = View.VISIBLE
            binding.ivDeleteSite.visibility = View.VISIBLE
            val animate = TranslateAnimation(
                0F, 0F,
                view.height.toFloat(), 0F
            )

            // duration of animation

            // duration of animation
            animate.duration = 500
            animate.fillAfter = true
            binding.ivAddSite.startAnimation(animate)
            binding.ivRedo.startAnimation(animate)
            binding.ivDeleteSite.startAnimation(animate)


            binding.ivPlus.setImageResource(R.drawable.ic_cross)

        }


    }


    fun onAddSiteClick(view: View){
        startActivity(Intent(this,AddSiteDetailActivity::class.java))
    }

    fun onRedoClientClick(view: View){

    }

    fun onDeleteSiteClick(view: View){
    }



    fun onItemClick(position: Int) {
          startActivity(Intent(this,ViewSiteDetailActivity::class.java)
              .putExtra("position",position)
              .putExtra("campaignType","old")
          )
    }

}