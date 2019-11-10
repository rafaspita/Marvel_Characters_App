package com.spitaliere.domain.features.characters.paging

import androidx.paging.DataSource
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.domain.features.characters.repository.CharacterRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
class CharacterDataSourceFactory(
    private val characterRepository: CharacterRepository
) : DataSource.Factory<Int, CharacterInfo>() {

    val composite: CompositeDisposable = CompositeDisposable()
    var listeners: PagingListeners = PagingListeners()

    override fun create(): DataSource<Int, CharacterInfo> = CharacterDataSource(
        characterRepository = characterRepository,
        compositeDisposable = composite,
        listeners = listeners
    )

}

data class PagingListeners(
    var showLoading: ((isLoading: Boolean) -> Unit) = {},
    var onError: ((error: Throwable?) -> Unit) = {},
    var retry: (() -> Unit) = {}
)