package com.example.cookery.rest

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(val repoService: Receipts) {

    fun getReceipts(mealType : String, number : Int, apiKey : String): Call<JsonObject> {
        return repoService.getReceiptsByType(mealType, number, apiKey)
    }
}