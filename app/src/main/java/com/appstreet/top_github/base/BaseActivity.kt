package com.appstreet.top_github.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.appstreet.top_github.interfaces.LayoutImplemment
import com.appstreet.top_github.interfaces.PermissionStatus
import com.appstreet.top_github.utils.CommonInt.BLOCKED_OR_NEVER_ASKED
import com.appstreet.top_github.utils.CommonInt.DENIED
import com.appstreet.top_github.utils.CommonInt.GRANTED
import com.appstreet.top_github.utils.UiUtils
import com.tedpark.tedpermission.rx2.TedRx2Permission
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseActivity<T : ViewModel>(clazz: KClass<T>) : AppCompatActivity(),
    LayoutImplemment {


    val model: T by viewModel(clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        }
        else super.onOptionsItemSelected(item)
    }

    protected fun checkPermissions(vararg permissions: String): Boolean {

        permissions.forEach {
            val permission = ContextCompat.checkSelfPermission(this, it)
            if (permission != 0)
                return false
        }

        return true
    }


    @PermissionStatus
    protected fun getPermissionStatus(androidPermissionName: String): Int {
        return if (ContextCompat.checkSelfPermission(
                this,
                androidPermissionName
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, androidPermissionName)) {
                BLOCKED_OR_NEVER_ASKED
            } else DENIED
        } else GRANTED
    }

    protected fun goToSettings() {
        val myAppSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
        myAppSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(myAppSettings)
        finish()
    }

    protected fun checkGPSEnabled(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    protected fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }

    protected fun isLocationEnabled(): Boolean {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }


    fun showProgressDialog(title: String?, message: String?, cancelable: Boolean = false) {
        UiUtils.showProgressDialog(this, title, message, cancelable)
    }
    fun hideProgressDialog() {
        UiUtils.dismissProgressDialog()
    }
}