package com.example.cookery.rest

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(private val repoService: Receipts) {

    fun getReceipts(mealType : String, number : Int): Call<JsonObject> {
        return repoService.getReceiptsByType(mealType, number)
    }

    fun getAnalyzedInstructions(receiptId : Long): Call<JsonArray> {
        return repoService.getAnalyzedInstructions(receiptId)
    }
}