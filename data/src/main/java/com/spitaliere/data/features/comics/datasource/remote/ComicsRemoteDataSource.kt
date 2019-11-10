package com.spitaliere.data.features.comics.datasource.remote

import com.spitaliere.data.features.comics.entity.ComicsCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
interface ComicsRemoteDataSource {

    fun getComicsByCharacterId(characterId: String) : Single<ComicsCache>
}