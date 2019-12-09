package com.example.cookery.di.component

import android.app.Application
import com.example.cookery.base.BaseApplication
import com.example.cookery.di.module.ActivityBindingModule
import com.example.cookery.di.module.ApplicationModule
import com.example.cookery.di.module.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, FragmentBindingModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<BaseApplication> {
    override fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}