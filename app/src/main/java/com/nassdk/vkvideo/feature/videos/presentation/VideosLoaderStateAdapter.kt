package com.nassdk.vkvideo.feature.videos.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nassdk.vkvideo.databinding.ItemErrorBinding
import com.nassdk.vkvideo.databinding.ItemProgressBinding

class VideosLoaderStateAdapter : LoadStateAdapter<RecyclerView.ViewHolder>() {

    override fun getStateViewType(loadState: LoadState): Int = when (loadState) {
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
        is LoadState.NotLoading -> error(message = "Not supported")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {
        when (holder) {
            is ProgressViewHolder -> holder.bind()
            is ErrorViewHolder -> holder.bind()
        }
    }

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
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() = Unit
    }

    class ErrorViewHolder(itemBinding: ItemErrorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() = Unit
    }

    private companion object {
        private const val PROGRESS = 1
        private const val ERROR = 0
    }
}