package com.osamaaftab.nutmeg.presentation.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.nutmeg.BR
import com.osamaaftab.nutmeg.presentation.adapter.GenericListAdapter


class AlbumViewHolder<T>(
    private val viewDataBinding: ViewDataBinding,
) : RecyclerView.ViewHolder(viewDataBinding.root),
    GenericListAdapter.Binder<T> {

    override fun bind(data: T) {
        viewDataBinding.setVariable(BR.album, data)
    }
}