package com.example.cookery.ui.receipts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.base.BaseViewModel
import com.example.cookery.globalClasses.MealTypes

//class ReceiptsViewModel @Inject constructor(repository: Repository) : ViewModel() {
class ReceiptsViewModel : BaseViewModel() {

    private val TAG = ReceiptsViewModel::class.java.simpleName

/*    private val _text = MutableLiveData<String>().apply {
        value = "This is receipts Fragment"
    }
    val text: LiveData<String> = _text*/

    var receipts: MutableLiveData<Array<String>>? = null

    //private val mRepository : Repository = repository

    fun getReceipts(): LiveData<Array<String>> {
        if (receipts == null) {
            receipts = MutableLiveData()
            receipts?.value = MealTypes.mealTypesArray
        }
        return receipts as MutableLiveData<Array<String>>
    }
}