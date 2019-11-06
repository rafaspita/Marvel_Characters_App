package com.spitaliere.domain.features.characters.paging

import androidx.paging.PageKeyedDataSource
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.domain.features.characters.repository.CharacterRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
class CharacterDataSource(
    private val characterRepository: CharacterRepository,
    private val compositeDisposable: CompositeDisposable,
    private val onLoading: OnPagingLoading
) : PageKeyedDataSource<Int, CharacterInfo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterInfo>
    ) = getCharacters(page = 0, adjacentPage =  1, requestedLoadSize =  params.requestedLoadSize, initialCallback =  callback)


    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterInfo>
    ) = getCharacters(page = params.key, adjacentPage = params.key + 1, requestedLoadSize = params.requestedLoadSize, callback = callback)


    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterInfo>
    ) = getCharacters(page = params.key, adjacentPage = params.key -1, requestedLoadSize = params.requestedLoadSize, callback = callback)

    private fun getCharacters(
        page: Int,
        adjacentPage: Int,
        requestedLoadSize: Int,
        initialCallback: LoadInitialCallback<Int, CharacterInfo>? = null,
        callback: LoadCallback<Int, CharacterInfo>? = null
    ){
        characterRepository.getCharacters((page * requestedLoadSize).toString())
            .doOnSubscribe {
                onLoading.show(true)
            }
            .subscribeBy(
                onError = {it.printStackTrace()},
                onSuccess = {
                    initialCallback?.onResult(it, null, adjacentPage)
                    callback?.onResult(it, adjacentPage)
                    onLoading.show(false)
                }
            ).addTo(compositeDisposable)
    }
}