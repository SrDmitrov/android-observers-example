package es.rudo.connectivitymanagerexample.livedata

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

/**
 *
 */
class LiveDataNetworkManager(context: Context) : LiveDataNetworkManagerInterface, LiveData<Boolean>() {
    private var connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        Log.d("LiveDataNetworkManager", "onActive: ")
        if (connectivityManager.activeNetwork == null) postValue(false)
        connectivityManager.registerNetworkCallback(super.networkRequest(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("LiveDataNetworkManager", "onInactive: ")
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}