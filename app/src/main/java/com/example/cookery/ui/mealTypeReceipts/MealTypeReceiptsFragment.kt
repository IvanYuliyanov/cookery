package com.example.cookery.ui.mealTypeReceipts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cookery.R
import com.example.cookery.adapters.MealTypeReceiptsAdapter
import com.example.cookery.base.BaseFragment
import com.example.cookery.databinding.MealTypeReceiptsFragmentBinding
import com.example.cookery.globalClasses.GridSpacingItemDecoration
import com.example.cookery.globalClasses.Utils
import com.example.cookery.ui.receiptInstructions.ReceiptInstructionsFragment


class MealTypeReceiptsFragment : BaseFragment(), MealTypeReceiptsAdapter.OnItemClickListener {
    private lateinit var binding: MealTypeReceiptsFragmentBinding
    private lateinit var mReceiptsViewModel: MealTypeReceiptsViewModel
    private lateinit var mAdapterMealType: MealTypeReceiptsAdapter

    private var mReceiptsArray = ArrayList<ReceiptModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meal_type_receipts, container, false)
        mReceiptsViewModel = ViewModelProviders.of(this).get(MealTypeReceiptsViewModel::class.java)
        binding.viewmodel = mReceiptsViewModel
        binding.lifecycleOwner = this

        init()
        setObserver()

        return binding.root
    }

    private fun init() {
        binding.mealTypeReceiptsRv.setHasFixedSize(true)

        val spanCount = 2 // 2 columns
        binding.mealTypeReceiptsRv.layoutManager = GridLayoutManager(activity, spanCount)

        val spacing = 20 // 20px
        val includeEdge = true
        binding.mealTypeReceiptsRv.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        mAdapterMealType = MealTypeReceiptsAdapter(activity as Context, mReceiptsArray, this)
        binding.mealTypeReceiptsRv.adapter = mAdapterMealType

        binding.mealTypeHeaderText.text = arguments?.getString(Utils.INTENT_TRANSFER_MEAL_TYPE)

    }

    private fun setObserver () {
        val mealType = arguments?.getString(Utils.INTENT_TRANSFER_MEAL_TYPE) ?: ""
        val receiptsCount = 30
        mReceiptsViewModel.getReceipts(mealType, receiptsCount).observe(this, Observer<ArrayList<ReceiptModel>> {
            Utils.enterTopAnimation(binding.mealTypeHeaderText, 500)
            mReceiptsArray = it
            mAdapterMealType.setData(mReceiptsArray)
        })
    }

    override fun onItemClick(receipt: ReceiptModel?) {
        val bundle = Bundle()
        bundle.putParcelable(Utils.INTENT_TRANSFER_RECEIPT, receipt)

        val receiptInstructionsFragment = ReceiptInstructionsFragment()
        receiptInstructionsFragment.arguments = bundle

        mOnFragmentInteractionListener?.onFragmentInteraction(receiptInstructionsFragment)
    }
}