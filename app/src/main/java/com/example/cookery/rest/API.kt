package com.example.cookery.rest

import com.example.cookery.globalClasses.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.HashMap
import java.util.concurrent.TimeUnit

class API {
/*    private fun <T> builder(endpoint: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(Utils.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(endpoint)
    }

    fun receipts(): Receipts {
        return builder(Receipts::class.java)
    }*/

    companion object{
        private const val TIME_OUT_IN_SEC = 30


        private fun <T> builder(endpoint: Class<T>): T {
/*            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    val requestBuilder = original.newBuilder()

                    *//*if (original.method().equals("POST", ignoreCase = true))
                        requestBuilder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mInputParameters.toString()))
                    else if (original.method().equals("PUT", ignoreCase = true))
                        requestBuilder.put(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mInputParameters.toString()))*//*
                    if (original.method().equals("GET", ignoreCase = true) || original.method().equals("DELETE", ignoreCase = true))
                        requestBuilder.url(getFullUrl(original))

                    val request = requestBuilder.build()

                    chain.proceed(request)
                }
                .connectTimeout(TIME_OUT_IN_SEC.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_IN_SEC.toLong(), TimeUnit.SECONDS)
                .build()*/

            return Retrofit.Builder()
                .baseUrl(Utils.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint)
        }

        fun receipts(): Receipts {
            return builder(Receipts::class.java)
        }

/*        private fun getFullUrl(request: Request): HttpUrl {
            val builder = request.url().newBuilder()
            val paramsMap =
                Gson().fromJson<HashMap<String, String>>(mInputParameters.toString(), object :
                    TypeToken<HashMap<String, String>>() {

                }.type)

            for (param in paramsMap.entries) {
                builder.addQueryParameter(param.key, param.value)
            }

            return builder.build()
        }*/
    }
}