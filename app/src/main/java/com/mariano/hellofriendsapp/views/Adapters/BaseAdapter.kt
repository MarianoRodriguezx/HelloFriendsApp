package com.mariano.hellofriendsapp.views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<BINDING: ViewBinding, ITEM> (
    private val dataSet: List<ITEM>
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<BINDING>>() {
    class BaseViewHolder<T: ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClick<T> {
        fun OnItemClick(item: T)
    }

    private lateinit var listener: OnItemClick<ITEM>

    constructor(list: List<ITEM>, listener: OnItemClick<ITEM>): this (list) {
        this.listener = listener
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        return BaseViewHolder(getBinding(LayoutInflater.from(parent.context), parent))
    }

    final override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        holder.binding.root.post {
            bindView(holder.binding, dataSet[position])
        }
    }

    final override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    final override fun getItemViewType(position: Int): Int {
        return position
    }

    final override fun getItemCount(): Int {
        return dataSet.size
    }

    protected abstract fun bindView(binding: BINDING, current: ITEM)

    protected abstract fun getBinding(layInf: LayoutInflater, parent: ViewGroup): BINDING
}