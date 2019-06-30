package com.example.githubfetcher.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        if (!imageUrl.isEmpty())
            Picasso.get().load(imageUrl).into(imageView);
    }
}
