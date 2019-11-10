package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.heroes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.FragmentHeroesBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseFragment
import com.spitaliere.marvelcharacters.presentation.platform.extension.assemble
import com.spitaliere.marvelcharacters.presentation.platform.extension.visible
import com.spitaliere.marvelcharacters.presentation.ui.characters.MainViewModel
import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.DetailsFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Rafael Spitaliere on 06/11/2019.
 **/
class HeroesFragment : BaseFragment<MainViewModel, FragmentHeroesBinding>() {

    override val viewModel: MainViewModel by sharedViewModel()
    private val heroesPagedAdapter by inject<HeroesPagedAdapter>()

    private val listener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            binding.error.errorContainer.visible(!recyclerView.canScrollVertically(1))
        }
    }

    override fun getLayout(): Int = R.layout.fragment_heroes

    override fun registerObservables() {
        with(viewModel){
            characterPageList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {},
                    onNext = {heroesPagedAdapter.submitList(it)}
                )

            errorLiveData.observe(this@HeroesFragment, Observer {
                binding.error.errorContainer.visible(it != null)
                when{
                    it == null -> binding.error.errorContainer.visible(false)
                    heroesPagedAdapter.currentList == null -> binding.error.errorContainer.visible()
                }
                setListernet(it != null)
                binding.error.errorText.text = it?.message
            })
        }
    }

    override fun setupView(root: View?) {
        activity?.title = activity?.getString(R.string.app_name)

        activity?.apply {
            binding.recyclerView.assemble(this.baseContext, heroesPagedAdapter)
        }

        heroesPagedAdapter.clickListener = {
            view?.findNavController()?.navigate(
                R.id.action_heroesFragment_to_informationsFragment5,
                Bundle().apply { putSerializable(DetailsFragment.HERO, it) }
            )
        }

        binding.error.tryAgain.setOnClickListener { viewModel.tryAgain() }
    }

    private fun setListernet(set: Boolean){
        if (set)
            binding.recyclerView.addOnScrollListener(listener)
        else
            binding.recyclerView.removeOnScrollListener(listener)
    }

}