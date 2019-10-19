package com.bargaintechnology.vantagecircle.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.bargaintechnology.vantagecircle.core.application.AppApplication

internal class CheckInternetConnection{

    fun check(): Boolean {
        val connectivityManager = AppApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetwork: NetworkInfo? = null
        activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork == null || !activeNetwork.isConnectedOrConnecting
    }
}