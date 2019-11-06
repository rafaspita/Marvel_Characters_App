package com.spitaliere.marvelcharacters

import android.app.Application
import com.spitaliere.data.platform.di.DataModules
import com.spitaliere.domain.platform.di.DomainModules
import com.spitaliere.marvelcharacters.presentation.ui.characters.CharactersPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelCharactersApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelCharactersApplication)
            modules(modules =
                DataModules.getDataModules()
                        + DomainModules.getModules()
                        + CharactersPresentationModule.getModule()
            )
        }
    }

}


