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
    private val listeners: PagingListeners
) : PageKeyedDataSource<Int, CharacterInfo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterInfo>
    ) = getCharacters(
        page = 0,
        adjacentPage =  1,
        requestedLoadSize =  params.requestedLoadSize,
        initialCallback =  callback,
        retry = getRetry(1, initialParams = params, initialCallback = callback)
    )

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterInfo>
    ) = getCharacters(
        page = params.key,
        adjacentPage = params.key + 1,
        requestedLoadSize = params.requestedLoadSize,
        callback = callback,
        retry = getRetry(2, params = params, loadCallback = callback)
    )


    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterInfo>
    ) = getCharacters(
        page = params.key,
        adjacentPage = params.key -1,
        requestedLoadSize = params.requestedLoadSize,
        callback = callback,
        retry = getRetry(3, params = params, loadCallback = callback)
    )

    private fun getCharacters(
        page: Int,
        adjacentPage: Int,
        requestedLoadSize: Int,
        initialCallback: LoadInitialCallback<Int, CharacterInfo>? = null,
        callback: LoadCallback<Int, CharacterInfo>? = null,
        retry: (() -> Unit) = {}
    ){
        characterRepository.getCharacters((page * requestedLoadSize).toString())
            .doOnSubscribe {
                listeners.showLoading(true)
                listeners.onError(null)
            }
            .subscribeBy(
                onError = {
                    listeners.showLoading(false)
                    listeners.retry = retry
                    listeners.onError(it)
                    it.printStackTrace()
                },
                onSuccess = {
                    initialCallback?.onResult(it, null, adjacentPage)
                    callback?.onResult(it, adjacentPage)
                    listeners.showLoading(false)
                }
            ).addTo(compositeDisposable)
    }

    private fun getRetry(
        function : Int,
        initialParams: LoadInitialParams<Int>? = null,
        initialCallback: LoadInitialCallback<Int, CharacterInfo>? = null,
        params: LoadParams<Int>? = null,
        loadCallback: LoadCallback<Int, CharacterInfo>? = null
    ): () -> Unit {
        return when(function){
            1 -> return { loadInitial(initialParams!!, initialCallback!!) }
            2 -> return { loadAfter(params!!, loadCallback!!) }
            3 -> return { loadBefore(params!!, loadCallback!!) }
            else -> { {} }
        }
    }
}