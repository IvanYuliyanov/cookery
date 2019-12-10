package com.example.cookery.globalClasses

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Utils {
    companion object {
        const val API_BASE_URL      = "https://api.spoonacular.com/"
        const val API_IMAGE_URLS    = "https://spoonacular.com/recipeImages/"
        const val API_KEY_VALUE     = "d4cd6bfb2e5a4f2b86210094f2f8c3f5"
        const val API_KEY           = "apiKey"

        const val INTENT_TRANSFER_MEAL_TYPE  = "type"
        const val INTENT_TRANSFER_RECEIPT    = "receipt"

        fun hasNetwork(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }
}