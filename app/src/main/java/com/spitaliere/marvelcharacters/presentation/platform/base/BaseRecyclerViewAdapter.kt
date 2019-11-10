package com.spitaliere.marvelcharacters.presentation.platform.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Rafael Spitaliere on 06/11/2019.
 **/
abstract class BaseRecyclerViewAdapter <ENTITY, VDB : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder<VDB>>() {

    var list: List<ENTITY> = listOf()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    var clickItem: ((ENTITY, view:VDB) -> Unit) = { _, _ ->  }

    @LayoutRes
    protected abstract fun getLayout(): Int

    open fun prepareView(item: ENTITY, binding: VDB, position: Int){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VDB> {
        val binding : VDB = DataBindingUtil.inflate(LayoutInflater.from(parent.context), getLayout(), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<VDB>, position: Int) {
        val item : ENTITY = list[position]
        holder.binding.setVariable(BR.item, item)
        prepareView(item, holder.binding, position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder<VDB : ViewDataBinding>(val binding: VDB) : RecyclerView.ViewHolder(binding.root)
}