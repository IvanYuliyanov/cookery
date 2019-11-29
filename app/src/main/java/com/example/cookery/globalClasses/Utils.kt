package com.example.cookery.globalClasses

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation

class Utils {
    companion object {
        const val API_BASE_URL      = "https://api.spoonacular.com/"
        const val API_IMAGE_URLS    = "https://spoonacular.com/recipeImages/"
        const val API_KEY           = "d4cd6bfb2e5a4f2b86210094f2f8c3f5"

        const val INTENT_TRANSFER_MEAL_TYPE  = "type"
        const val INTENT_TRANSFER_RECEIPT    = "receipt"

        fun animateRecyclerViewItemOnScroll(item: View?, duration: Long) {
            if (item != null) {
                val scaleAnimation = ScaleAnimation(
                    0f,
                    1.0f,
                    0f,
                    1.0f,
                    ScaleAnimation.RELATIVE_TO_SELF,
                    0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF,
                    0.5f
                )
                scaleAnimation.duration = duration

                val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
                alphaAnimation.duration = duration

                val animationSet = AnimationSet(false)
                animationSet.addAnimation(scaleAnimation)
                animationSet.addAnimation(alphaAnimation)

                item.startAnimation(animationSet)
            }
        }

        fun enterTopAnimation(view : View, duration: Long) {
            view.post {
                val translateToolbar = TranslateAnimation(0f, 0f, (-view.measuredHeight).toFloat(), 0f)
                translateToolbar.duration = duration

                val toolbarAlphaAnimation = AlphaAnimation(0.0f, 1.0f)
                toolbarAlphaAnimation.duration = duration

                val animationSet = AnimationSet(false)
                animationSet.addAnimation(translateToolbar)
                animationSet.addAnimation(toolbarAlphaAnimation)

                view.startAnimation(animationSet)
                view.visibility = View.VISIBLE
            }
        }
    }
}