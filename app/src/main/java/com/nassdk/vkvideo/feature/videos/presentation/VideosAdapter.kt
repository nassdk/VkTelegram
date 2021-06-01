package com.nassdk.vkvideo.feature.videos.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.databinding.ItemVideoBinding
import com.nassdk.vkvideo.feature.videos.domain.model.VideoModel

class VideosAdapter : PagingDataAdapter<VideoModel, VideosAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        itemBinding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(item = getItem(position)!!)

    class ViewHolder(private val itemBinding: ItemVideoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: VideoModel) {

            with(itemBinding) {

                title.text = item.title
                description.text = item.description
                views.text = root.context.getString(R.string.videos_screen_views_count, item.views)

                image.load(uri = item.image) {
                    transformations(RoundedCornersTransformation(radius = 8f))
                }
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VideoModel>() {
            override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
