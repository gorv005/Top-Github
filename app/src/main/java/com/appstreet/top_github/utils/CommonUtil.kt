package com.appstreet.top_github.utils

import android.content.Context
import android.net.ConnectivityManager
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import android.text.format.DateFormat;
import java.math.BigDecimal
import java.math.RoundingMode


object CommonUtil {


    fun getDay(date:String?):String{
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dayOfTheWeek = DateFormat.format("EEEE", format.parse(date)) as String // Thursday
        return dayOfTheWeek
    }

    fun getTemperatureInCelcuis(temp:Double?):String{

        val t = temp?.minus(273.15)

        return BigDecimal(t!!).setScale(2, RoundingMode.HALF_EVEN).toString()+"C"


    }
    fun getTemperatureInCelcuisInt(temp:Double?):String{

        val t = temp?.minus(273.15)

        return BigDecimal(t!!).setScale(0, RoundingMode.HALF_EVEN).toString()+" C"


    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    const val ONE = 1
    const val TWO = 2
    const val THREE = 3


}