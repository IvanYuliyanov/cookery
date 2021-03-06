package com.example.cookery.di.module

import com.example.cookery.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}