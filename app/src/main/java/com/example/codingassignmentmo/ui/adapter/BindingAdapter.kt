package com.example.codingassignmentmo.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapters{

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView?, url: String?) {
            Picasso.get()
                .load(url)
                .into(imageView)
        }
    }

}
