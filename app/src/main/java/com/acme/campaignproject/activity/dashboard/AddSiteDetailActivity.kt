package com.acme.campaignproject.activity.dashboard

import android.Manifest
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.acme.campaignproject.R
import com.acme.campaignproject.databinding.ActivityAddSiteDetailBinding
import com.acme.campaignproject.utility.NetworkUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class AddSiteDetailActivity : AppCompatActivity(), LocationListener {

    lateinit var binding:ActivityAddSiteDetailBinding
    var cal = Calendar.getInstance()
    var yy = 0
    var mm = 0
    var dd = 0
    var imageUrl = ""
    var REQUEST_IMAGE_CAPTURE=101

    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_add_site_detail)

    }


    fun btnCloseClick(view: View){
     this.finish()
    }

    fun btnSaveClick(view: View){

        if (binding.etUnitId.text.isNullOrEmpty()
            || binding.etSiteNo.text.isNullOrEmpty()
            || binding.etStartDate.text.isNullOrEmpty()
            || binding.etLocation.text.isNullOrEmpty()
            || binding.etLatitude.text.isNullOrEmpty()
            || binding.etLongitude.text.isNullOrEmpty()
            || binding.etHeight.text.isNullOrEmpty()
            || binding.etWidth.text.isNullOrEmpty()
            || binding.etTotalArea.text.isNullOrEmpty()){
            Toast.makeText(this,"Fill all the fields", Toast.LENGTH_LONG).show()
        }else{
            showSuccessMessage()
        }

    }

    fun addImage(view: View){
        if (!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this,"Check your Internet Connection and Try Again", Toast.LENGTH_LONG).show()
        }else {

            if (!checkPermissions()) {
                    requestPermissions()
            }
            else {
                dispatchTakePictureIntent()
            }



        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
          //  val imageuri = data?.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.ivCampaignImage.setImageBitmap(imageBitmap)

            getlatlong()

        }
    }




    private fun getlatlong() {
        if (!checkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions()
            }
        }
        else {
            getLastLocation()
        }
    }



    private fun getLastLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }
    override fun onLocationChanged(location: Location) {
        Log.d("lastlocation", "Latitude: " + location.latitude + " , Longitude: " + location.longitude)

            binding.etLatitude.setText(""+ location!!.latitude)
            binding.etLongitude.setText(""+ location!!.longitude)
    }



    private fun showSnackbar(
        mainTextStringId: String, actionStringId: String,
        listener: View.OnClickListener
    ) {
        Toast.makeText(this, mainTextStringId, Toast.LENGTH_LONG).show()
    }
    private fun checkPermissions(): Boolean {
        if(ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )==PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )==PackageManager.PERMISSION_GRANTED
            &&  ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )==PackageManager.PERMISSION_GRANTED){
            return true
        }else{
            return false
        }
    }



    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA
                ,Manifest.permission.ACCESS_COARSE_LOCATION
                ,Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }



    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                    Log.i(TAG, "User interaction was cancelled.")
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                -> {
                    // Permission granted.
                    dispatchTakePictureIntent()
                }
                else -> {
                    showSnackbar("Permission was denied", "Settings",
                        View.OnClickListener {
                            // Build intent that displays the App settings screen.
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                Build.DISPLAY, null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
    companion object {
        private val TAG = "LocationProvider"
        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }





    fun addMoreSiteClick(view: View){

    }



    fun showSuccessMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.custom_emailsent, null)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        val tvResubmit = view.findViewById<TextView>(R.id.tvResubmit)
        tvResubmit.visibility = View.INVISIBLE
        tvMsg.text =  "Campaign Added Successfully"
        val btnClose = view.findViewById<Button>(R.id.btnClose)
        builder.setView(view)
        val dialog = builder.create()
        if (dialog != null) {
            dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        }
        btnClose.setOnClickListener {
            if (dialog != null) {
                dialog.dismiss()
               // this.finish()
            }
        }
        if (dialog != null) {
            dialog.show()
        }
    }


    fun showCalendar(view: View){
        yy = cal.get(Calendar.YEAR)
        mm = cal.get(Calendar.MONTH)
        dd = cal.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(this,
            { view, year, month, dayOfMonth ->
                yy = year
                mm = month
                dd = dayOfMonth
                val dateStr = String.format(Locale.getDefault(), "%02d-%02d-%02d", dd, mm + 1, yy)
                binding.etStartDate.setText(dateStr)
            }, yy, mm, dd
        )
        dialog.datePicker.minDate = System.currentTimeMillis()
        dialog.show()
    }


}