package com.spitaliere.marvelcharacters

import android.app.Application
import com.spitaliere.data.platform.di.DataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelCharactersApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelCharactersApplication)
            modules(modules =
                DataModules.getDataModules()
            )
        }
    }

}


