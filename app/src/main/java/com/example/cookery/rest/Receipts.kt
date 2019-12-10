package com.example.cookery.rest

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Receipts {
    @GET("recipes/search")
    @Headers("Content-Type: application/json")
    fun getReceiptsByType(@Query("query") mealType: String, @Query("number") number: Int): Call<JsonObject>

    @GET("recipes/{id}/analyzedInstructions")
    @Headers("Content-Type: application/json")
    fun getAnalyzedInstructions(@Path("id") receiptId : Long): Call<JsonArray>
}