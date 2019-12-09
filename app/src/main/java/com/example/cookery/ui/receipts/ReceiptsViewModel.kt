package com.example.cookery.ui.receipts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.base.BaseViewModel
import com.example.cookery.globalClasses.MealTypes
import com.example.cookery.rest.Repository
import javax.inject.Inject

class ReceiptsViewModel @Inject constructor(repository: Repository) : BaseViewModel() {
    private val TAG = ReceiptsViewModel::class.java.simpleName

    private val mRepository : Repository = repository

    var mReceipts: MutableLiveData<Array<String>>? = null


    fun getReceipts(): LiveData<Array<String>> {
        if (mReceipts == null) {
            mReceipts = MutableLiveData()
            mReceipts?.value = MealTypes.mealTypesArray
        }
        return mReceipts as MutableLiveData<Array<String>>
    }
}