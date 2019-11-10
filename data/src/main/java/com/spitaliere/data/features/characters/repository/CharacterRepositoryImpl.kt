package com.spitaliere.data.features.characters.repository

import androidx.room.EmptyResultSetException
import com.spitaliere.data.features.characters.datasource.local.CharacterLocalDataSource
import com.spitaliere.data.features.characters.datasource.remote.CharacterRemoteDataSource
import com.spitaliere.data.features.characters.entity.OffSetCache
import com.spitaliere.data.features.characters.entity.mapCharacterToDomain
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

    override fun getCharacters(offset: String): Single<List<CharacterInfo>> =
        localDataSource.getCharactersByOffset(offset)
            .onErrorResumeNext { throwable ->
                when (throwable) {
                    is EmptyResultSetException -> return@onErrorResumeNext Single.just(listOf())
                    else -> return@onErrorResumeNext Single.error(throwable)
                }
            }.flatMap { listCache ->
                when {
                    listCache.isEmpty() -> return@flatMap getCharactersRemote(offset)
                    else -> return@flatMap Single.just(listCache.mapCharacterToDomain())
                }
            }

    private fun getCharactersRemote(offset: String) =
        remoteDataSource.getCharacters(offset)
            .flatMap { list ->
                localDataSource.saveOffSet(OffSetCache(offset, list.map { it.id }))
                localDataSource.saveCharacters(list)
                Single.just(list.mapCharacterToDomain())
            }
}