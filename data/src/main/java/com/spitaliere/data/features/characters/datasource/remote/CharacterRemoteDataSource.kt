package com.spitaliere.data.features.characters.datasource.remote

import com.spitaliere.data.features.characters.entity.CharacterCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
interface CharacterRemoteDataSource {

    fun getCharacters(
       offset: String
    ): Single<List<CharacterCache>>

}