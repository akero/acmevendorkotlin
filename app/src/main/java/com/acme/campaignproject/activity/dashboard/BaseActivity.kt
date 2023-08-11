package com.acme.campaignproject.activity.dashboard

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
            logMessage("dialogg", "not null")
        }
    }

    fun hideProgressDialog() {
        if (pDialog != null) {
            if (pDialog!!.isShowing) {
                pDialog!!.dismiss()
            }
        }
    }

    fun logMessage(_TAG: String?, _message: String?) {
        Log.e(_TAG, _message!!)
    }

    interface OnDialogOkListener {
        fun onOk()
    }

    fun showAlert(msg: String?) {

//        new iOSDialogBuilder(BaseActivity.this)
//                .setTitle("Alert")
//                .setSubtitle(msg)
//                .setBoldPositiveLabel(true)
//                .setCancelable(false)
//                .setPositiveListener(getString(R.string.ok),new iOSDialogClickListener() {
//                    @Override
//                    public void onClick(iOSDialog dialog) {
//                        dialog.dismiss();
//                    }
//                })
//                .build().show();
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setCancelable(false)
        val inflater = LayoutInflater.from(this@BaseActivity)
        val view = inflater.inflate(R.layout.custom_popup, null)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        tvMsg.typeface = Typeface.createFromAsset(assets, "fonts/pregular.otf")
        tvMsg.text = msg
        val btnOk = view.findViewById<Button>(R.id.btnOk)
        btnOk.typeface = Typeface.createFromAsset(assets, "fonts/pbold.otf")
        builder.setView(view)
        val dialog = builder.create()
        if (dialog != null) {
            dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        }
        btnOk.setOnClickListener {
            if (dialog != null) {
                dialog.dismiss()
            }
        }
        if (dialog != null) {
            dialog.show()
        }
    }

    fun showAlert(msg: String?, listener: OnDialogOkListener) {

//        new iOSDialogBuilder(BaseActivity.this)
//                .setTitle("Confirmation")
//                .setSubtitle(msg)
//                .setBoldPositiveLabel(true)
//                .setCancelable(false)
//                .setPositiveListener(getString(R.string.ok),new iOSDialogClickListener() {
//                    @Override
//                    public void onClick(iOSDialog dialog) {
//                        //dialog.dismiss();
//                        if (dialog != null) {
//                            dialog.dismiss();
//                            listener.onOk();
//                        }
//                    }
//                })
//                .build().show();
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setCancelable(false)
        val inflater = LayoutInflater.from(this@BaseActivity)
        val view = inflater.inflate(R.layout.custom_popup, null)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        tvMsg.typeface = Typeface.createFromAsset(assets, "fonts/pregular.otf")
        tvMsg.text = msg
        val btnOk = view.findViewById<Button>(R.id.btnOk)
        btnOk.typeface = Typeface.createFromAsset(assets, "fonts/pbold.otf")
        builder.setView(view)
        val dialog = builder.create()
        if (dialog != null) {
            dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        }
        btnOk.setOnClickListener {
            if (dialog != null) {
                dialog.dismiss()
                listener.onOk()
            }
        }
        if (dialog != null) {
            dialog.show()
        }
    }

    override fun onDestroy() {
        hideProgressDialog()
        super.onDestroy()
    }
}