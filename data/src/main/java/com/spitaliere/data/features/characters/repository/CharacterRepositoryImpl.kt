package com.spitaliere.data.features.characters.repository

import com.spitaliere.data.features.characters.datasource.local.CharacterLocalDataSource
import com.spitaliere.data.features.characters.datasource.remote.CharacterRemoteDataSource
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.domain.features.characters.repository.CharacterRepository
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override fun getCharacters(limit: String, offset: String): Single<List<CharacterInfo>> = Single.just(null)

}