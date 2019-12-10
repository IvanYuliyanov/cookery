package com.example.cookery.ui.mealTypeReceipts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.base.BaseViewModel
import com.example.cookery.rest.Repository
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MealTypeReceiptsViewModel  @Inject constructor(repository: Repository) : BaseViewModel() {
    private val TAG = MealTypeReceiptsViewModel::class.java.simpleName

    var mRepository = repository

    var mReceipts: MutableLiveData<ArrayList<ReceiptModel>>? = null
    var mReceiptsResp: ArrayList<ReceiptModel> = ArrayList()

    fun getReceipts(mealType : String, count : Int): LiveData<ArrayList<ReceiptModel>> {
        if (mReceipts == null) {
            mReceipts = MutableLiveData()
            getLoading().value = VISIBLE
            loadReceipts(mealType, count)
        }
        return mReceipts as MutableLiveData<ArrayList<ReceiptModel>>
    }

    private fun loadReceipts(mealType : String, count : Int) {
        mRepository.getReceipts( mealType, count).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(TAG, call.request().toString())
                Log.d(TAG, response.body().toString())

                response.body()?.let {
                    val resultsJsonArray = it.getAsJsonArray("results")

                    for (i in resultsJsonArray) {
                        val jsonObject = i.asJsonObject
                        mReceiptsResp.add(
                            ReceiptModel(
                                jsonObject.get("id").asLong,
                                jsonObject.get("title").asString,
                                jsonObject.get("image").asString,
                                jsonObject.get("servings").asString,
                                jsonObject.get("readyInMinutes").asInt))
                    }

                    mReceipts?.value = mReceiptsResp
                    getLoading().value = GONE
                }
            }

            override fun onFailure(call: Call<JsonObject>, throwable: Throwable) {
                Log.d(TAG, throwable.toString())
            }
        })
    }
}