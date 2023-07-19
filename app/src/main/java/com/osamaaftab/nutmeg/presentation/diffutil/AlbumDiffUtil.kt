package com.osamaaftab.nutmeg.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.osamaaftab.nutmeg.domain.model.AlbumModel

class AlbumDiffUtil : DiffUtil.ItemCallback<AlbumModel>() {
    override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
        return oldItem.id == newItem.id
    }
}