package com.example.cookery.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    var showLoading: MutableLiveData<Int>? = null
    var infoText: MutableLiveData<String>? = null

    fun getLoading(): MutableLiveData<Int> {

        if (showLoading == null) {
            showLoading = MutableLiveData()
            showLoading?.value = GONE
        }

        return showLoading as MutableLiveData<Int>
    }

    fun getInfoTxt(): MutableLiveData<String> {

        if (infoText == null) {
            infoText = MutableLiveData()
            infoText?.value = ""
        }

        return infoText as MutableLiveData<String>
    }

    companion object {
        const val VISIBLE = 0
        const val GONE = 8
    }

}