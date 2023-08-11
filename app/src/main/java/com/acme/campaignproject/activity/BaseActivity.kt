package com.acme.campaignproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater

import android.widget.TextView
import android.graphics.Typeface
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.acme.campaignproject.R

abstract class BaseActivity : AppCompatActivity() {
    var pDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun addProgress(): AlertDialog? {
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setCancelable(false)
        val inflater = LayoutInflater.from(this@BaseActivity)
        val view = inflater.inflate(R.layout.custom_progess, null)
        builder.setView(view)
        val dialog = builder.create()
        if (dialog != null) {
            dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        }
        return dialog
    }

    fun showProgressDialog() {
        if (pDialog == null) {
            pDialog = addProgress()
            pDialog!!.show()
        } else {
            if (pDialog != null) {
                if (!pDialog!!.isShowing) {
                    pDialog!!.show()
                } else {
                    pDialog!!.dismiss()
                }
            }
        }
    }

    fun hideProgressDialog() {
        if (pDialog != null) {
            if (pDialog!!.isShowing) {
                pDialog!!.dismiss()
            }
        }
    }





    override fun onDestroy() {
        hideProgressDialog()
        super.onDestroy()
    }
}
