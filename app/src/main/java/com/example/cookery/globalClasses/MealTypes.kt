package com.example.cookery.globalClasses

import com.example.cookery.R

class MealTypes {
    companion object {
        const val SOUP          = "soup"
        const val SALAD         = "salad"
        const val APPETIZER     = "appetizer"
        const val MAIN_COURSE   = "main course"
        const val SIDE_DISH     = "side dish"
        const val DESSERT       = "dessert"

        val mealTypesArray = arrayOf(SOUP, SALAD, APPETIZER, MAIN_COURSE, SIDE_DISH, DESSERT)

        fun getMealImageByType(type : String) : Int{
            return when (type) {
                SOUP        -> R.drawable.soup_image
                SALAD       -> R.drawable.salad_image
                APPETIZER   -> R.drawable.appetizer_image
                MAIN_COURSE -> R.drawable.main_course_image
                SIDE_DISH   -> R.drawable.side_dish_image
                DESSERT     -> R.drawable.dessert_image
                else        -> R.drawable.main_course_image
            }
        }
    }
}