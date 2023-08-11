package com.acme.campaignproject.activity.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.acme.campaignproject.R
import com.acme.campaignproject.adapters.CampaignListAdapter
import com.acme.campaignproject.adapters.ClientListAdapter
import com.acme.campaignproject.databinding.ActivityMainBinding
import com.acme.campaignproject.viewmodel.MainViewModel
import org.json.JSONArray
import org.json.JSONObject


class AdminDashboardActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding:ActivityMainBinding
    lateinit var jsonArray:JSONArray
    var showMenus = false
    
    //for showing the image from url just follow the Myjet pack project and use the adapter class
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val quoteService = RetroiftHelper.getInstance().create(QuoteService::class.java)
//        val repository = QuoteRepository(quoteService)
//        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)
//        mainViewModel.quotes.observe(this, Observer {
//            Log.d("quotelistdata",it.results.toString())
//        })


        clientList()
        campaignList()
//        binding.etSearch.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // Do Nothing
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                var filteredJSONArray = JSONArray()
////                for (i in 0..jsonArray.length()){
////                    if (jsonArray.getJSONObject(i).getString("name").equals()){
////
////                    }
////                }
//
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                // Do Nothing
//            }
//        })

    }

    fun campaignList(){

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


    fun clientList(){



        val layoutManager1 = GridLayoutManager(this, 2)
        val layoutManager2 = GridLayoutManager(this, 2)

        binding.rvClientList.setLayoutManager(layoutManager1)
        binding.rvVenderList.setLayoutManager(layoutManager2)

         jsonArray = JSONArray()
        var jsonObjectairbnb = JSONObject()
        var jsonObjecthyundai = JSONObject()
        var jsonObjectford = JSONObject()
        var jsonObjectpatanjli = JSONObject()

        jsonObjectairbnb.put("image",R.drawable.aone)
        jsonObjecthyundai.put("image",R.drawable.aaone)
        jsonObjectford.put("image",R.drawable.aaaone)
        jsonObjectpatanjli.put("image",R.drawable.aaaaone)

        jsonObjectairbnb.put("name","Airbnb")
        jsonObjecthyundai.put("name","Hyundai")
        jsonObjectford.put("name","Ford")
        jsonObjectpatanjli.put("name","Patanjli")



        jsonArray.put(jsonObjectairbnb)
        jsonArray.put(jsonObjecthyundai)
        jsonArray.put(jsonObjectford)
        jsonArray.put(jsonObjectpatanjli)



        val adapter1 = ClientListAdapter(this,jsonArray)
        val adapter2 = ClientListAdapter(this,jsonArray)

        binding.rvVenderList.adapter = adapter1
        binding.rvClientList.adapter = adapter2

    }




    fun onPlusClick(view: View){

        if (showMenus){
            binding.ivPlus.setImageResource(R.drawable.ic_add)
            showMenus=false


            val animate = TranslateAnimation(
                0F, 0F, 0F,
                view.height.toFloat()
            )
            animate.duration = 0
            binding.ivAddClient.startAnimation(animate)
            binding.ivRedo.startAnimation(animate)
            binding.ivDeleteClient.startAnimation(animate)
            binding.ivAddClient.visibility = View.INVISIBLE
            binding.ivRedo.visibility = View.INVISIBLE
            binding.ivDeleteClient.visibility = View.INVISIBLE

        }else{
            showMenus=true

            binding.ivAddClient.visibility = View.VISIBLE
            binding.ivRedo.visibility = View.VISIBLE
            binding.ivDeleteClient.visibility = View.VISIBLE
            val animate = TranslateAnimation(
                0F, 0F,
                view.height.toFloat(), 0F
            )

            // duration of animation

            // duration of animation
            animate.duration = 500
            animate.fillAfter = true
            binding.ivAddClient.startAnimation(animate)
            binding.ivRedo.startAnimation(animate)
            binding.ivDeleteClient.startAnimation(animate)


            binding.ivPlus.setImageResource(R.drawable.ic_cross)

        }


    }


    fun onAddClientClick(view: View){
        startActivity(Intent(this,AddClientActivity::class.java))
    }

    fun onAddVenderClick(view: View){
        startActivity(Intent(this,AddVenderActivity::class.java))
    }

    fun onDeleteClientClick(view: View){

    }



    @SuppressLint("ResourceAsColor")
    fun btnCompaignClick(view: View){


        binding.rvCampaignList.visibility = View.VISIBLE
        binding.rvClientList.visibility = View.GONE
        binding.rvVenderList.visibility = View.GONE

        binding.tvCompaign.setBackgroundResource(R.drawable.primaryround)
        binding.tvVender.setBackgroundResource(R.color.coloryellow)
        binding.tvClient.setBackgroundResource(R.color.coloryellow)

        binding.tvCompaign.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tvVender.setTextColor(Color.parseColor("#0089BE"))
        binding.tvClient.setTextColor(Color.parseColor("#0089BE"))

    }



    @SuppressLint("ResourceAsColor")
    fun btnVenderClick(view: View){

        binding.rvCampaignList.visibility = View.GONE
        binding.rvClientList.visibility = View.GONE
        binding.rvVenderList.visibility = View.VISIBLE

        binding.tvVender.setBackgroundResource(R.drawable.primaryround)
        binding.tvCompaign.setBackgroundResource(R.color.coloryellow)
        binding.tvClient.setBackgroundResource(R.color.coloryellow)


        binding.tvClient.setTextColor(Color.parseColor("#0089BE"))
        binding.tvCompaign.setTextColor(Color.parseColor("#0089BE"))
        binding.tvVender.setTextColor(Color.parseColor("#FFFFFF"))

    }






    @SuppressLint("ResourceAsColor")
    fun btnClientClick(view: View){

        binding.rvCampaignList.visibility = View.GONE
        binding.rvClientList.visibility = View.VISIBLE
        binding.rvVenderList.visibility = View.GONE

        binding.tvCompaign.setBackgroundResource(R.color.coloryellow)
        binding.tvClient.setBackgroundResource(R.drawable.primaryround)
        binding.tvVender.setBackgroundResource(R.color.coloryellow)

        binding.tvCompaign.setTextColor(Color.parseColor("#0089BE"))
        binding.tvClient.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tvVender.setTextColor(Color.parseColor("#0089BE"))

    }

    fun onItemClick(position: Int) {
        startActivity(Intent(this,ViewSiteDetailActivity::class.java)
            .putExtra("position",position)
            .putExtra("campaignType","old")
        )
    }


}