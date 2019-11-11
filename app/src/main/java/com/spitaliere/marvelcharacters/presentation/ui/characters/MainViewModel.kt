package com.spitaliere.marvelcharacters.presentation.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.domain.features.characters.paging.CharacterDataSourceFactory
import com.spitaliere.domain.features.characters.usecase.RetryUseCase
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseViewModel
import com.spitaliere.marvelcharacters.presentation.platform.extension.invokeOnBackground
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
class MainViewModel(
   private  val dataSourceFactory: CharacterDataSourceFactory,
   private val retryUseCase: RetryUseCase
) : BaseViewModel() {

    var characterPageList : Observable<PagedList<CharacterInfo>>
    val isLoading = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()


    init {
        disposables.add(dataSourceFactory.composite)

        with( dataSourceFactory.listeners){
            showLoading = { isLoading.postValue(it) }
            onError = { errorLiveData.postValue(it) }
        }

        val pageSize = 20

        val config: PagedList.Config = PagedList.Config.Builder()
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

    fun tryAgain() = retryUseCase
        .invokeOnBackground { dataSourceFactory.listeners.retry() }
        .subscribeBy(
            onError = {errorLiveData.postValue(it)}
        )


}