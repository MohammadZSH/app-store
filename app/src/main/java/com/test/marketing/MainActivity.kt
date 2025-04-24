package com.test.marketing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.marketing.ui.screens.MainScreen
import com.test.marketing.ui.theme.MarketingTheme
import com.test.marketing.ui.viewModel.MarketingAppViewModel
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast

class MainActivity : ComponentActivity() {
    val CODE_OF_IT = 4555
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app_id_from_notif = intent.getIntExtra("app_id",-1)
        var appCurrentId = if (app_id_from_notif==-1) AppPrefs.getAppCurrentId() else app_id_from_notif

        val isPermitGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        if (isPermitGranted== PackageManager.PERMISSION_GRANTED){
            call()
        }else{
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),CODE_OF_IT)
        }

        enableEdgeToEdge()
        setContent {
            val viewModel: MarketingAppViewModel = viewModel()
            MarketingTheme {
                Surface() {
                    MainScreen(viewModel, LocalActivity.current!!,appCurrentId!!)
                }
            }
        }
    }

    private fun call(){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.setData(Uri.parse("tel:12345678900"))
        startActivity(callIntent)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode==CODE_OF_IT){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                call()
            }else{
                Toast.makeText(this,"not granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
