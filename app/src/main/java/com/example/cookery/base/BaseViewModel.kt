package com.example.cookery.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookery.R
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    companion object {
        const val VISIBLE = 0
        const val GONE    = 8
    }

    @Inject
    lateinit var context : Context

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
}