package com.example.cookery.ui.mealTypeReceipts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.base.BaseViewModel
import com.example.cookery.rest.Repository
import javax.inject.Inject

class MealTypeReceiptsViewModel  @Inject constructor(repository: Repository) : BaseViewModel() {
    var mRepository = repository

    fun getReceipts(mealType : String, count : Int): LiveData<ArrayList<ReceiptModel>> {
        getLoading().value = VISIBLE

        return mRepository.getReceipts( mealType, count) as MutableLiveData<ArrayList<ReceiptModel>>
    }
}