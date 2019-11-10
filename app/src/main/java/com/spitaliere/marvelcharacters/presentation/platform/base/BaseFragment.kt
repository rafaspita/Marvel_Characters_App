package com.spitaliere.marvelcharacters.presentation.platform.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding : VB

    @LayoutRes
    protected abstract fun getLayout() : Int

    protected open fun registerObservables(){}

    protected abstract fun setupView(root: View?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        setupView(binding.root)
        registerObservables()
        return binding.root
    }
}