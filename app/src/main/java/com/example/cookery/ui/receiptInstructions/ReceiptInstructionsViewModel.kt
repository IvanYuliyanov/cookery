package com.example.cookery.ui.receiptInstructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.base.BaseViewModel
import com.example.cookery.rest.Repository
import javax.inject.Inject

class ReceiptInstructionsViewModel @Inject constructor(repository: Repository) : BaseViewModel() {
    var mRepository = repository

    fun getInstructions(id : Long): LiveData<ArrayList<ReceiptInstructionsModel>> {
        getLoading().value = VISIBLE

        return mRepository.getAnalyzedInstructions(id) as MutableLiveData<ArrayList<ReceiptInstructionsModel>>
    }
}