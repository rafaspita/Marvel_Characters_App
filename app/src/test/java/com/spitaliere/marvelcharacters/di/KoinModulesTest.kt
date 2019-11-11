package com.spitaliere.marvelcharacters.di

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.spitaliere.data.platform.di.DataModules
import com.spitaliere.domain.platform.di.DomainModules
import com.spitaliere.marvelcharacters.presentation.ui.characters.MainPresentationModule
import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.DetailsModule
import com.spitaliere.marvelcharacters.presentation.ui.splashscreen.SplashScreenModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 **/
@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE, sdk = [Build.VERSION_CODES.O])
class KoinModulesTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `check koin modules`() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        koinApplication {
            androidContext(appContext)
            modules(modules =
                    DataModules.getDataModules()
                    + DomainModules.getModules()
                    + MainPresentationModule.getModule()
                    + DetailsModule.getModule()
                    + SplashScreenModule.getModule()
            )
        }.checkModules()
    }
}