package com.spitaliere.marvelcharacters.presentation.ui.characters

import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ActivityMainBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseActivity
import com.spitaliere.marvelcharacters.presentation.platform.extension.animateMove
import com.spitaliere.marvelcharacters.presentation.platform.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()

    private lateinit var navigationController : NavController

    override fun getLayout(): Int = R.layout.activity_main

    override fun showBackButton(): Boolean = false

    override fun getToolbarTitle(): String = getString(R.string.app_name)

    override fun setupView() {
        navigationController = findNavController(R.id.fragmentNavHost)
        NavigationUI.setupActionBarWithNavController(this, navigationController)

        binding.animationView.animateMove(y= -500f, runnable =  Runnable { binding.animationView.visible(false) })
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()

    companion object{
        fun getIntent(context: Context) : Intent = Intent(context, MainActivity::class.java )
    }

}