package com.spitaliere.marvelcharacters.presentation.ui.characters

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ActivityMainBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseActivity
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
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()

}