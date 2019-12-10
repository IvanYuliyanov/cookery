package com.example.cookery.ui.receiptInstructions

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.cookery.R
import com.example.cookery.base.BaseFragment
import com.example.cookery.databinding.ReceiptInstructionsFragmentBinding
import com.example.cookery.globalClasses.Animations
import com.example.cookery.globalClasses.Utils
import com.example.cookery.ui.mealTypeReceipts.ReceiptModel


class ReceiptInstructionsFragment : BaseFragment() {
    companion object {
        const val IMAGE_SIZE = "-636x393.jpg"
    }

    private var mReceiptModel: ReceiptModel? = null
    private lateinit var mBinding: ReceiptInstructionsFragmentBinding
    private lateinit var mReceiptInstructionsViewModel: ReceiptInstructionsViewModel

    private var mReceiptInstructions : ArrayList<ReceiptInstructionsModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt_instructions, container, false)
        mReceiptInstructionsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ReceiptInstructionsViewModel::class.java)
        mBinding.viewmodel = mReceiptInstructionsViewModel
        mBinding.lifecycleOwner = this

        init()
        setObserver()
        setUI()

        return mBinding.root
    }

    private fun init() {
        mReceiptModel = arguments?.getParcelable(Utils.INTENT_TRANSFER_RECEIPT)
    }

    private fun setUI() {
        activity?.let {
            Glide
                .with(it)
                .load(Utils.API_IMAGE_URLS + mReceiptModel?.id + IMAGE_SIZE)
                .centerCrop()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        mBinding.receiptIv.setImageResource(R.drawable.ic_launcher_background)
                        return true
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        //do something when picture already loaded
                        Animations.animateRecyclerViewItemOnScroll(mBinding.receiptIv, 500)
                        return false
                    }
                })
                .into(mBinding.receiptIv)
        }

        mReceiptModel?.let {
            mBinding.receiptTitleTv.text = it.title
            mBinding.receiptTimeTv.text = TextUtils.concat(it.readyInMinutes.toString(), " ", getString(R.string.min))
            mBinding.servingsTv.text = TextUtils.concat(it.servings, " ", getString(R.string.servings))
        }

        Animations.enterTopAnimation(mBinding.descContainer, 750)
    }

    private fun setObserver() {
        val receiptId : Long = mReceiptModel?.id ?: 0
        mReceiptInstructionsViewModel.getInstructions(receiptId).observe(this, Observer {
            mReceiptInstructions = it

            for (receiptInstruction in mReceiptInstructions){
                val step = TextView(activity)
                step.text = HtmlCompat.fromHtml((TextUtils.concat("<b><big><big>", receiptInstruction.stepNumber, "</big></big></b>", " ", receiptInstruction.instruction).toString()), HtmlCompat.FROM_HTML_MODE_LEGACY)
                step.setTextColor(ContextCompat.getColor(activity as Context, android.R.color.white))
                step.setPadding(32,16,32,16)
                step.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
                mBinding.instructionStepsContainer.addView(step)
            }

            Animations.animateRecyclerViewItemOnScroll(mBinding.instructionStepsContainer, 500)
        })
    }
}