package com.skechers.kotlinsampleapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kawasdk.Fragment.fragmentFarmLocation
import com.kawasdk.Utils.InterfaceKawaEvents
import com.kawasdk.Utils.KawaMap
import org.json.JSONObject


class HomeActivity : AppCompatActivity(), InterfaceKawaEvents {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        mapView = findViewById(R.id.mapView)
//        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
        KawaMap.initKawaMap(getResources().getString(R.string.kawa_api_key)) // kawa api key
        KawaMap.initSegment(this@HomeActivity,getResources().getString(R.string.segment_api_key)); //Segment api key
        KawaMap.initSmartlook(getResources().getString(R.string.smartlook_api_key)); // Smartlook api key

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.kawaMapView, fragmentFarmLocation()).commit()


    }


    override fun initKawaMap(isValid: Boolean) {
        Log.e("TAG", "initKawaMap: >>> $isValid")
        if (isValid == true) {
            KawaMap.setFooterBtnBgColorAndTextColor(Color.BLUE, Color.WHITE)
            KawaMap.setInnerBtnBgColorAndTextColor(Color.WHITE, Color.BLACK)
            KawaMap.setHeaderBgColorAndTextColor(Color.BLUE, Color.WHITE)
            KawaMap.isEditEnable = false
            KawaMap.isMergeEnable = true
            KawaMap.isFarmDetailsEnable = true
            KawaMap.isOtherFarmDetailsEnable = false
            KawaMap.isFormEnable = false
            KawaMap.isSaveResultEnable = true
            KawaMap.isBahasaEnable = false
            KawaMap.isFlyToLocationEnable = false
        } else {
            val alertDialog = AlertDialog.Builder(this@HomeActivity).create()
            alertDialog.setTitle(resources.getString(com.kawasdk.R.string.app_name))
            alertDialog.setMessage("KAWA api key not found.")
            alertDialog.setIcon(com.kawasdk.R.mipmap.ic_launcher)
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, which -> finish() }
            alertDialog.show()
            //Toast.makeText(this, "KAWA api key not found.", Toast.LENGTH_LONG).show();
        }
    }

    override fun onkawaSelect(data: com.google.gson.JsonObject?) {
        Log.e("TAG", "initKawaMap: "+ data)
    }

    override fun onkawaUpdate(centerposition: JSONObject?) {
        Log.e("TAG", "initKawaMap: "+ centerposition)
    }

    override fun onkawaSubmit(data: String?) {
        Log.e("TAG", "initKawaMap: "+ data)
    }
}