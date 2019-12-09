package com.example.cookery.ui.receipts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookery.R
import com.example.cookery.adapters.ReceiptsAdapter
import com.example.cookery.databinding.ReceiptsFragmentBinding
import com.example.cookery.globalClasses.MealTypes
import com.example.cookery.ui.mealTypeReceipts.MealTypeReceiptsFragment
import com.example.cookery.base.BaseFragment
import com.example.cookery.globalClasses.Utils


class ReceiptsFragment : BaseFragment(), ReceiptsAdapter.OnItemClickListener {
    lateinit var mBinding: ReceiptsFragmentBinding
    lateinit var mReceiptsViewModel: ReceiptsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mReceiptsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ReceiptsViewModel::class.java)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mBinding.viewmodel = mReceiptsViewModel
        mBinding.lifecycleOwner = this

        init()

        return mBinding.root
    }

    private fun init() {
        mBinding.mealTypesRv?.setHasFixedSize(true)
        mBinding.mealTypesRv?.layoutManager = LinearLayoutManager(activity)

        mBinding.mealTypesRv?.adapter = ReceiptsAdapter(MealTypes.mealTypesArray, this)
    }

    private fun setObserver() {
        mReceiptsViewModel.getReceipts().observe(this, Observer {

        })
    }

    override fun onItemClick(mealType: String) {
        val bundle = Bundle()
        bundle.putString(Utils.INTENT_TRANSFER_MEAL_TYPE, mealType)

        val mealTypeReceiptsFragment = MealTypeReceiptsFragment()
        mealTypeReceiptsFragment.arguments = bundle

        mOnFragmentInteractionListener?.onFragmentInteraction(mealTypeReceiptsFragment)
    }
}