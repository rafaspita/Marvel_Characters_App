package com.spitaliere.data.features.comics.repository

import androidx.room.EmptyResultSetException
import com.spitaliere.data.features.comics.datasource.local.ComicsLocalDataSource
import com.spitaliere.data.features.comics.datasource.remote.ComicsRemoteDataSource
import com.spitaliere.data.features.comics.entity.ComicsCache
import com.spitaliere.data.features.comics.entity.mapComicsToDomain
import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.domain.features.comics.repository.ComicsRepository
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
class ComicsRepositoryImpl(
    private val remoteDataSource: ComicsRemoteDataSource,
    private val localDataSource: ComicsLocalDataSource
) : ComicsRepository {

    override fun getCharacterComicsById(characterId: String): Single<List<ComicsInfo>> =
        localDataSource.getComicsByCharacterId(characterId)
            .onErrorResumeNext { throwable ->
                when (throwable) {
                    is EmptyResultSetException -> return@onErrorResumeNext Single.just(ComicsCache(characterId, listOf()))
                    else -> return@onErrorResumeNext Single.error(throwable)
                }
            }.flatMap { comicsCache ->
                when {
                    comicsCache.comicsInfo.isEmpty() -> return@flatMap getComicsRemote(characterId)
                    else -> return@flatMap Single.just(comicsCache.comicsInfo.mapComicsToDomain())
                }
            }

    private fun getComicsRemote(characterId: String) =
        remoteDataSource.getComicsByCharacterId(characterId)
            .flatMap {
                localDataSource.saveCharacterComics(it)
                Single.just(it.comicsInfo.mapComicsToDomain())
            }


}