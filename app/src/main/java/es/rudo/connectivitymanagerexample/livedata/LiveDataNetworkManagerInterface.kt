package es.rudo.connectivitymanagerexample.livedata

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

interface LiveDataNetworkManagerInterface {
    val networkCallback: ConnectivityManager.NetworkCallback
    fun networkRequest(): NetworkRequest =
        NetworkRequest.Builder().apply {
            // addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            // addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        }.build()
}