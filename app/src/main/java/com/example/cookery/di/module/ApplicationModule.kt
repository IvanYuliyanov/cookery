package com.example.cookery.di.module

import android.content.Context
import com.example.cookery.base.BaseApplication
import com.example.cookery.globalClasses.Utils
import com.example.cookery.rest.Receipts
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class ApplicationModule (private val application: BaseApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(application.cacheDir, cacheSize)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(Utils.API_KEY, Utils.API_KEY_VALUE)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                /*
                 *  Leveraging the advantage of using Kotlin,
                 *  we initialize the request and change its header depending on whether
                 *  the device is connected to Internet or not.
                 */
                request = if (Utils.hasNetwork(application)!!)
                    /*
                    *  If there is Internet, get the cache that was stored 5 seconds ago.
                    *  If the cache is older than 5 seconds, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-age' attribute is responsible for this behavior.
                    */
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    /*
                    *  If there is no Internet, get the cache that was stored 7 days ago.
                    *  If the cache is older than 7 days, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-stale' attribute is responsible for this behavior.
                    *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                    */
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl(Utils.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): Receipts {
        return retrofit.create(Receipts::class.java)
    }
}