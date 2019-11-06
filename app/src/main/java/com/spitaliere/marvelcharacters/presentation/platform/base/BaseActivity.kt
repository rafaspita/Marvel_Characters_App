package com.spitaliere.marvelcharacters.presentation.platform.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
abstract class BaseActivity <VM: BaseViewModel, VDB : ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewModel: VM
    protected lateinit var binding : VDB

    @LayoutRes
    protected abstract fun getLayout() : Int

    protected open fun getToolbarTitle() : String = ""

    protected open fun showBackButton(): Boolean = true

    protected open fun registerObservables(){}

    protected open fun setupView(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView()
        setToolbar()
        setupView()
        registerObservables()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setToolbar() {
        supportActionBar?.title = getToolbarTitle()
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton())
    }

    private fun setBindingView() {
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

}