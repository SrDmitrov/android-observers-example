package es.rudo.connectivitymanagerexample.flow

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * Implementation of [FlowConnectivityObserver]
 */
class NetworkFlowConectivityObserver(private val context: Context) : FlowConnectivityObserver {

    /**
     * the connectivity manager obtains the actual status from
     * the system
     */
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     * This implementation's purpose is to convert the [connectivityManager] statuses to flows
     * When a specific callback is triggered, this function will send a value
     *
     * The callbackFlow callws inside needs
     * ```<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />```
     * to be added to AndroidManifest
     */
    override fun observe(): Flow<FlowConnectivityObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(FlowConnectivityObserver.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(FlowConnectivityObserver.Status.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(FlowConnectivityObserver.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(FlowConnectivityObserver.Status.Unavailable) }
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose{
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged() // Ensures only firing when the value is changed
    }

}