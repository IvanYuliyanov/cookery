package com.example.cookery.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.cookery.di.util.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    var mOnFragmentInteractionListener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(fragment: BaseFragment)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

        if (context is OnFragmentInteractionListener) {
            mOnFragmentInteractionListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }
}