package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ComicDialogBinding
import com.spitaliere.marvelcharacters.presentation.platform.extension.setImage

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 **/
class ComicDialogImpl : ComicDialog {

    private var dialog : Dialog? = null

    override fun show(context: Context, comicsInfo: ComicsInfo) {
        val binding: ComicDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.comic_dialog, null, false)
        dialog = Dialog(context)

        binding.item = comicsInfo
        binding.imageView.setImage(comicsInfo.thumbnail, horizontal = false)
        binding.pages.text = context.getString(R.string.s_pages, comicsInfo.pageCount)
        binding.format.text = context.getString(R.string.format_s, comicsInfo.format)
        binding.description.text = context.getString(R.string.description_s, comicsInfo.description)

        binding.root.setOnClickListener { dismiss() }

        dialog?.setContentView(binding.root)
        dialog?.show()
    }

    override fun dismiss() {
        dialog?.dismiss()
    }
}