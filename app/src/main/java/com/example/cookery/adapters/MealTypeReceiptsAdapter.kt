package com.example.cookery.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookery.R
import com.example.cookery.globalClasses.Animations
import com.example.cookery.globalClasses.Utils
import com.example.cookery.interfaces.BindAdapter
import com.example.cookery.ui.mealTypeReceipts.ReceiptModel

class MealTypeReceiptsAdapter (context: Context, receipts : ArrayList<ReceiptModel>?, listener: OnItemClickListener) : RecyclerView.Adapter<MealTypeReceiptsAdapter.ReceiptViewHolder>(), BindAdapter {
    override fun setData(items: ArrayList<ReceiptModel>) {
        mReceipts = items
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(mealType: ReceiptModel?)
    }

    private var mListener : OnItemClickListener = listener
    private var mReceipts = receipts
    private val mContext               = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        return ReceiptViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.receipt_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mReceipts?.size ?: 0
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receiptModel = mReceipts?.get(position)
        holder.title.text = receiptModel?.title

        Glide
            .with(mContext)
            .load(Utils.API_IMAGE_URLS + receiptModel?.id + "-480x360.jpg")
            .centerCrop()
            //.placeholder(R.drawable.progress_animation)
            .into(holder.image)

        holder.container.setOnClickListener {
            mListener.onItemClick(mReceipts?.get(position))
        }

        holder.time.text = receiptModel?.readyInMinutes.toString() + " min"

        Animations.animateRecyclerViewItemOnScroll(holder.container, 250)
    }

    class ReceiptViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var container : ConstraintLayout = v.findViewById(R.id.receipt_container)
        var title : TextView             = v.findViewById(R.id.receipt_title_tv)
        var time  : TextView             = v.findViewById(R.id.receipt_time_tv)
        var image : ImageView            = v.findViewById(R.id.receipt_iv)
    }
}