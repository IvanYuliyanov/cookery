package com.example.cookery.ui.mealTypeReceipts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookery.base.BaseViewModel
import com.example.cookery.globalClasses.Utils
import com.example.cookery.rest.API
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class MealTypeReceiptsViewModel : BaseViewModel() {
    private val TAG = MealTypeReceiptsViewModel::class.java.simpleName

    var mReceipts: MutableLiveData<ArrayList<ReceiptModel>>? = null
    var receiptsResp: ArrayList<ReceiptModel> = ArrayList()

    fun getReceipts(mealType : String, count : Int): LiveData<ArrayList<ReceiptModel>> {
        if (mReceipts == null) {
            mReceipts = MutableLiveData()
            getLoading().value = VISIBLE
            loadReceipts(mealType, count)
        }
        return mReceipts as MutableLiveData<ArrayList<ReceiptModel>>
    }

    private fun loadReceipts(mealType : String, count : Int) {
        API.receipts().getReceiptsByType( mealType, count, Utils.API_KEY).enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(TAG, call.request().toString())
                Log.d(TAG, response.body().toString())

                response.body()?.let {
                    val resultsJsonArray = it.getAsJsonArray("results")

                    for (i in resultsJsonArray) {
                        val jsonObject = i.asJsonObject
                        receiptsResp.add(
                            ReceiptModel(
                                jsonObject.get("id").asLong,
                                jsonObject.get("title").asString,
                                jsonObject.get("image").asString,
                                jsonObject.get("servings").asString,
                                jsonObject.get("readyInMinutes").asInt))
                    }

                    mReceipts?.value = receiptsResp
                    getLoading().value = GONE
                }
            }

            override fun onFailure(call: Call<JsonObject>, throwable: Throwable) {
                Log.d(TAG, throwable.toString())
            }
        })
    }
}