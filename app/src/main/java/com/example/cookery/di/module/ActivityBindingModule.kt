package com.example.cookery.di.module

import com.example.cookery.MainActivity
import com.example.cookery.ui.receipts.ReceiptsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    //@ContributesAndroidInjector(modules = [(FragmentBindingModule::class)])
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}