package com.acme.campaignproject.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.acme.campaignproject.R
import com.acme.campaignproject.activity.dashboard.CampaignListActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import org.json.JSONArray

class ClientListAdapter(val context: Context, val jsonArray: JSONArray) :RecyclerView.Adapter<ClientListAdapter.ViewHolder>() {





    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val ivCampaign: ImageView = itemView.findViewById(R.id.ivCampaign)
        val tvClientName: TextView = itemView.findViewById(R.id.tvClientName)
        val tvUnitNo: TextView = itemView.findViewById(R.id.tvUnitNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_client_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvClientName.setText(jsonArray.getJSONObject(position).getString("name"))
        holder.ivCampaign.setImageResource(jsonArray.getJSONObject(position).getInt("image"))

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,CampaignListActivity::class.java))
        }

    // holder.tvUnitNo.setText("Unit Id:- ")

//        Glide.with(this)
//            .load(media)
//            .into(holder.ivCampaign)

    }

    override fun getItemCount(): Int {
        return jsonArray.length()
    }
}