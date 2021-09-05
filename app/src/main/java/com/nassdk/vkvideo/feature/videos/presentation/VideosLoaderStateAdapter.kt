package com.nassdk.vkvideo.feature.videos.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nassdk.vkvideo.databinding.ItemErrorBinding
import com.nassdk.vkvideo.databinding.ItemProgressBinding

class VideosLoaderStateAdapter : LoadStateAdapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) = Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RecyclerView.ViewHolder = when (loadState) {
        LoadState.Loading -> ProgressViewHolder(
            itemBinding = ItemProgressBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        is LoadState.Error -> ErrorViewHolder(
            itemBinding = ItemErrorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        is LoadState.NotLoading -> error(message = "Not supported")
    }

    class ProgressViewHolder(itemBinding: ItemProgressBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    class ErrorViewHolder(itemBinding: ItemErrorBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}