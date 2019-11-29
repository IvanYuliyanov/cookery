package com.example.cookery.ui.receipts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
    lateinit var binding: ReceiptsFragmentBinding
    lateinit var receiptsViewModel: ReceiptsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        receiptsViewModel = ViewModelProviders.of(this).get(ReceiptsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        init()

        return binding.root
    }

    private fun init() {
        binding.mealTypesRv?.setHasFixedSize(true)
        binding.mealTypesRv?.layoutManager = LinearLayoutManager(activity)

        binding.mealTypesRv?.adapter = ReceiptsAdapter(MealTypes.mealTypesArray, this)
    }

    override fun onItemClick(mealType: String) {
        val bundle = Bundle()
        bundle.putString(Utils.INTENT_TRANSFER_MEAL_TYPE, mealType)

        val mealTypeReceiptsFragment = MealTypeReceiptsFragment()
        mealTypeReceiptsFragment.arguments = bundle

        mOnFragmentInteractionListener?.onFragmentInteraction(mealTypeReceiptsFragment)
    }
}