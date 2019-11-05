package com.spitaliere.domain.platform.di

import com.spitaliere.domain.features.comics.di.ComicsDomainModule

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
object DomainModules {

    fun getModules() = listOf(
        ComicsDomainModule.getModule()
    )
}