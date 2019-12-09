package com.example.cookery.di.module

import com.example.cookery.ui.mealTypeReceipts.MealTypeReceiptsFragment
import com.example.cookery.ui.receiptInstructions.ReceiptInstructionsFragment
import com.example.cookery.ui.receipts.ReceiptsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun bindReceiptsFragment() : ReceiptsFragment

    @ContributesAndroidInjector
    abstract fun bindReceiptInstructionsFragment() : ReceiptInstructionsFragment

    @ContributesAndroidInjector
    abstract fun bindMealTypeReceiptsFragment() : MealTypeReceiptsFragment
}
