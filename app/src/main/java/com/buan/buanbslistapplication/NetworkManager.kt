package com.buan.buanbslistapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

class NetworkManager {

    companion object {
        fun checkNetworkState(context: Context): Int {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(ConnectivityManager::class.java)
            val network: Network = connectivityManager.activeNetwork ?: return 0
            val actNetwork: NetworkCapabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return 0

            return when {
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> 1
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> 2
                else -> 0
            }
        }
    }

}