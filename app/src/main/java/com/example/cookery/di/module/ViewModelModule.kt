package com.example.cookery.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookery.di.util.ViewModelFactory
import com.example.cookery.di.util.ViewModelKey
import com.example.cookery.ui.receipts.ReceiptsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/*@Singleton*/
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ReceiptsViewModel::class)
    abstract fun receiptsViewModel(receiptsViewModel: ReceiptsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}