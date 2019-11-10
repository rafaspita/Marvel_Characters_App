package com.spitaliere.marvelcharacters.presentation.ui.splashscreen

import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ActivitySplashScreenBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseActivity
import com.spitaliere.marvelcharacters.presentation.platform.extension.animateAlpha
import com.spitaliere.marvelcharacters.presentation.platform.extension.animateMove
import com.spitaliere.marvelcharacters.presentation.platform.extension.setListener
import com.spitaliere.marvelcharacters.presentation.ui.characters.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 **/
class SplashScreenActivity : BaseActivity<SplashScreenViewModel, ActivitySplashScreenBinding>() {

    override val viewModel: SplashScreenViewModel by viewModel()

    override fun getLayout(): Int = R.layout.activity_splash_screen

    override fun setupView() {
        binding.logo.animateAlpha()
        binding.animationView.animateMove(y = 500f,
            runnable = Runnable {
                binding.animationView.playAnimation()
                binding.animationView.setListener(
                    Runnable {
                        startActivity(MainActivity.getIntent(this), ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this,
                            Pair<View, String>(binding.animationView, ViewCompat.getTransitionName(binding.animationView))
                        ).toBundle())
                        finishAfterTransition()
                    })
            }
        )
    }
}