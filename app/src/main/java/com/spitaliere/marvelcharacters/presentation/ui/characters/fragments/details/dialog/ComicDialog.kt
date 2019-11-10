package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.dialog

import android.content.Context
import com.spitaliere.domain.features.comics.model.ComicsInfo

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 **/
interface ComicDialog {

    fun show(context: Context, comicsInfo: ComicsInfo)

    fun dismiss()
}