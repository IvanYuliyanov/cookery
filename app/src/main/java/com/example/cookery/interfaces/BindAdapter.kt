package com.example.cookery.interfaces

import com.example.cookery.ui.mealTypeReceipts.ReceiptModel

interface BindAdapter {
    fun setData(items : ArrayList<ReceiptModel>)
}