package com.example.cookery.globalClasses

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cookery.interfaces.BindAdapter
import com.example.cookery.ui.mealTypeReceipts.ReceiptModel

class BindingUtils {
    companion object {
        @JvmStatic @BindingAdapter(value = ["data_items"])
        fun setRecyclerViewProperties(recyclerView: RecyclerView, items: ArrayList<ReceiptModel>?) {
            if (recyclerView.adapter is BindAdapter) {
                if (items != null) {
                    (recyclerView.adapter as BindAdapter).setData(items)
                }
            }
        }
    }
}