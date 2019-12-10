package com.example.cookery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cookery.R
import com.example.cookery.globalClasses.Animations
import com.example.cookery.globalClasses.MealTypes

class ReceiptsAdapter (mealTypes : Array<String>, listener: OnItemClickListener) : RecyclerView.Adapter<ReceiptsAdapter.MealTypeViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(mealType: String)
    }

    private var mListener : OnItemClickListener = listener
    private var mMealTypes = mealTypes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealTypeViewHolder {
        return MealTypeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_type_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mMealTypes.size
    }

    override fun onBindViewHolder(holder: MealTypeViewHolder, position: Int) {
        holder.title.text = mMealTypes[position]

        holder.image.setImageResource(MealTypes.getMealImageByType(mMealTypes[position]))

        holder.container.setOnClickListener {
            mListener.onItemClick(mMealTypes[position])
        }

        Animations.animateRecyclerViewItemOnScroll(holder.container, 250)
    }

    class MealTypeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var container : ConstraintLayout = v.findViewById(R.id.meal_item_container)
        var title : TextView             = v.findViewById(R.id.meal_type_title_tv)
        var image : ImageView            = v.findViewById(R.id.meat_type_iv)
    }
}