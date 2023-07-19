package com.osamaaftab.nutmeg.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.setImageUrl(url: String) {
    this.load(url)
}

