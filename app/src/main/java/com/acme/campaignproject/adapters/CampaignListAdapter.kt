package com.acme.campaignproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.dashboard.AdminDashboardActivity
import com.acme.campaignproject.activity.dashboard.CampaignListActivity
import com.acme.campaignproject.activity.dashboard.ClientDashBoardActivity
import com.acme.campaignproject.activity.vender.VenderDashBoardActivity
import org.json.JSONArray

class CampaignListAdapter(val context: Context, val jsonArray: JSONArray) :RecyclerView.Adapter<CampaignListAdapter.ViewHolder>() {





    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val ivCampaign: ImageView = itemView.findViewById(R.id.ivCampaign)
        val tvSiteNo: TextView = itemView.findViewById(R.id.tvSiteNo)
        val tvUnitNo: TextView = itemView.findViewById(R.id.tvUnitNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_campaign_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.tvSiteNo.setText("Site No. "+jsonArray.getJSONObject(position).getString("sitenumber"))
        holder.tvUnitNo.setText("Unit Id:- "+jsonArray.getJSONObject(position).getString("unitnumber"))
        holder.itemView.setOnClickListener {
            if (context is CampaignListActivity){
                context.onItemClick(position)
            }else if (context is ClientDashBoardActivity){
                context.onItemClick(position)
            } else if (context is VenderDashBoardActivity){
                context.onItemClick(position)
            } else if (context is AdminDashboardActivity){
                context.onItemClick(position)
            }


        }
    }

    override fun getItemCount(): Int {
        return jsonArray.length()
    }
}