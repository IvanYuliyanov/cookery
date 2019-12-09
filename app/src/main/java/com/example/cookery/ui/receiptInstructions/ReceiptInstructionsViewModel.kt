package com.example.cookery.ui.receiptInstructions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.base.BaseViewModel
import com.example.cookery.globalClasses.Utils
import com.example.cookery.rest.Repository
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ReceiptInstructionsViewModel @Inject constructor(repository: Repository) : BaseViewModel() {
    private val TAG = ReceiptInstructionsViewModel::class.java.simpleName

    var mRepository = repository

    var mReceiptInstructions: MutableLiveData<ArrayList<ReceiptInstructionsModel>>? = null
    var mReceiptsResp: ArrayList<ReceiptInstructionsModel> = ArrayList()

    fun getInstructions(id : Long): LiveData<ArrayList<ReceiptInstructionsModel>> {
        if (mReceiptInstructions == null) {
            mReceiptInstructions = MutableLiveData()
            getLoading().value = VISIBLE
            loadInstructions(id)
        }
        return mReceiptInstructions as MutableLiveData<ArrayList<ReceiptInstructionsModel>>
    }

    private fun loadInstructions(id : Long) {
        mRepository.getAnalyzedInstructions(id, Utils.API_KEY).enqueue(object : retrofit2.Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Log.d(TAG, call.request().toString())
                Log.d(TAG, response.body().toString())

                response.body()?.let {
                    try {
                        val resultsJsonArray = it.asJsonArray
                        val resultsJsonObject: JsonObject = resultsJsonArray.get(0) as JsonObject
                        val stepsJsonArray = resultsJsonObject.get("steps") as JsonArray

                        for (i in stepsJsonArray) {
                            val jsonObject = i.asJsonObject
                            mReceiptsResp.add(
                                ReceiptInstructionsModel(
                                    jsonObject.get("number").asString + ".",
                                    jsonObject.get("step").asString))
                        }

                        mReceiptInstructions?.value = mReceiptsResp
                        getLoading().value = GONE
                    } catch (e : Exception){
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<JsonArray>, throwable: Throwable) {
                Log.d(TAG, throwable.toString())
            }
        })
    }
}