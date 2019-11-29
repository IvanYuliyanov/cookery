package com.example.cookery.base

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    var mOnFragmentInteractionListener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(fragment: BaseFragment)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mOnFragmentInteractionListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }
}