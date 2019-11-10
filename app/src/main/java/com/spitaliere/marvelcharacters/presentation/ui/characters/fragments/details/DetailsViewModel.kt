package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details

import androidx.lifecycle.MutableLiveData
import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.domain.features.comics.usecases.GetCharacterComicsByIdCase
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseViewModel
import com.spitaliere.marvelcharacters.presentation.platform.extension.StateMachineSingle
import com.spitaliere.marvelcharacters.presentation.platform.extension.ViewState
import com.spitaliere.marvelcharacters.presentation.platform.extension.invokeOnBackground
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Rafael Spitaliere on 07/11/2019.
 **/
class DetailsViewModel(
    private val comicsByIdCase: GetCharacterComicsByIdCase
) : BaseViewModel() {

    val heroComics = MutableLiveData<ViewState<List<ComicsInfo>>>()

    fun getHeroComics(characterId: String) =
        comicsByIdCase.invokeOnBackground(characterId)
            .compose(StateMachineSingle(heroComics))
            .subscribeBy(
                onError = {}
            )
            .addTo(disposables)
}