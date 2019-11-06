package com.spitaliere.marvelcharacters.presentation.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.domain.features.characters.paging.CharacterDataSourceFactory
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
class CharactersViewModel(
    dataSourceFactory: CharacterDataSourceFactory
) : BaseViewModel() {

    var characterPageList : Observable<PagedList<CharacterInfo>>
    val isLoading = MutableLiveData<Boolean>()

    init {
        disposables.add(dataSourceFactory.composite)
        dataSourceFactory.onLoad.show = { isLoading.postValue(it) }

        val pageSize = 20

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize * 3)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        characterPageList = RxPagedListBuilder(dataSourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()
    }
}