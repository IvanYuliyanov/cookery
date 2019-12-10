package com.example.cookery.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Singleton


@Module
class SharedPreferencesModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}