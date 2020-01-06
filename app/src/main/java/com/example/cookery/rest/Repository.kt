package com.example.cookery.rest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookery.ui.mealTypeReceipts.ReceiptModel
import com.example.cookery.ui.receiptInstructions.ReceiptInstructionsModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val repoService: Receipts) {
    companion object{
        private val TAG = Repository::class.java.simpleName
    }

    private var mReceipts : MutableLiveData<ArrayList<ReceiptModel>> = MutableLiveData()
    private var mReceiptInstructions: MutableLiveData<ArrayList<ReceiptInstructionsModel>> = MutableLiveData()


    fun getReceipts(mealType : String, number : Int): LiveData<ArrayList<ReceiptModel>> {
        repoService.getReceiptsByType(mealType, number).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(TAG, call.request().toString())
                Log.d(TAG, response.body().toString())
                val receiptsResp: ArrayList<ReceiptModel> = ArrayList()

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
                                jsonObject.get("readyInMinutes").asInt)
                        )
                    }
                }
                mReceipts.value = receiptsResp
            }

            override fun onFailure(call: Call<JsonObject>, throwable: Throwable) {
                Log.d(TAG, throwable.toString())
                mReceipts.value = ArrayList()
            }
        })

        return mReceipts
    }

    fun getAnalyzedInstructions(receiptId : Long): LiveData<ArrayList<ReceiptInstructionsModel>> {
        repoService.getAnalyzedInstructions(receiptId).enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Log.d(TAG, call.request().toString())
                Log.d(TAG, response.body().toString())

                val receiptsResp: ArrayList<ReceiptInstructionsModel> = ArrayList()

                response.body()?.let {
                    try {
                        val resultsJsonArray = it.asJsonArray
                        val resultsJsonObject: JsonObject = resultsJsonArray.get(0) as JsonObject
                        val stepsJsonArray = resultsJsonObject.get("steps") as JsonArray

                        for (i in stepsJsonArray) {
                            val jsonObject = i.asJsonObject
                            receiptsResp.add(
                                ReceiptInstructionsModel(
                                    jsonObject.get("number").asString + ".",
                                    jsonObject.get("step").asString)
                            )
                        }
                    } catch (e : Exception){
                        e.printStackTrace()
                    }
                }
                mReceiptInstructions.value = receiptsResp
            }

            override fun onFailure(call: Call<JsonArray>, throwable: Throwable) {
                Log.d(TAG, throwable.toString())
                mReceiptInstructions.value = ArrayList()
            }
        })

        return mReceiptInstructions
    }
}