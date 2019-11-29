package com.example.cookery.di.module

import com.example.cookery.globalClasses.Utils
import com.example.cookery.rest.Receipts
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*@Singleton*/
@Module(includes = [ViewModelModule::class])
class ApplicationModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): Receipts {
        return retrofit.create(Receipts::class.java)
    }
}