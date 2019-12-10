package com.example.cookery.di.module

import com.example.cookery.globalClasses.Utils
import com.example.cookery.rest.Receipts
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(Utils.API_KEY, Utils.API_KEY_VALUE)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
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