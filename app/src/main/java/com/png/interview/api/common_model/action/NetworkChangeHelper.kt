package com.png.interview.api.common_model.action

import android.annotation.TargetApi
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkChangeHelper
@Inject constructor(
    private val app: Application,
    private val connectivityManager: ConnectivityManager
) {

    fun getNetworkChangeFlow(): Flow<NetworkConnectionType> {
        var receiver: BroadcastReceiver? = null
        return callbackFlow<NetworkConnectionType> {
            val newReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val networkType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getNetworkConnectionTypeFromTransport()
                    } else {
                        getNetworkConnectionTypeFromNetworkInfo()
                    }
                    offer(networkType)
                }
            }
            app.registerReceiver(newReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
            receiver = newReceiver
            awaitClose()
        }
            .onCompletion {
                receiver?.let {
                    app.unregisterReceiver(it)
                }
            }
    }

    fun getNetworkConnectionTypeFromNetworkInfo(): NetworkConnectionType {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.let { networkInfo ->
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> NetworkConnectionType.WIFI
                ConnectivityManager.TYPE_MOBILE -> NetworkConnectionType.MOBILE
                else -> NetworkConnectionType.NONE
            }
        } ?: NetworkConnectionType.NONE
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun getNetworkConnectionTypeFromTransport(): NetworkConnectionType {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return when {
            capabilities == null -> NetworkConnectionType.NONE
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkConnectionType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkConnectionType.MOBILE
            else -> NetworkConnectionType.NONE
        }
    }

    enum class NetworkConnectionType {
        MOBILE, WIFI, NONE
    }
}