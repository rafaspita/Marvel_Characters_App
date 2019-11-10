package com.spitaliere.data.platform.di

import com.spitaliere.data.api.di.ApiModule
import com.spitaliere.data.features.characters.di.CharacterDataModule
import com.spitaliere.data.features.comics.di.ComicsDataModule

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
object DataModules {

    fun getDataModules() = listOf(
        ApiModule.getModule(),
        CharacterDataModule.getModule(),
        ComicsDataModule.getModule()
    )
}