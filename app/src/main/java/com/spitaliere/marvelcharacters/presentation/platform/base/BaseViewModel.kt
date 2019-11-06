package com.spitaliere.marvelcharacters.presentation.platform.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
abstract class BaseViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }
}