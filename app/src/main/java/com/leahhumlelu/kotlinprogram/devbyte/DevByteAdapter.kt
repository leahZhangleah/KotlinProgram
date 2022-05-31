package com.leahhumlelu.kotlinprogram.devbyte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leahhumlelu.kotlinprogram.databinding.DevByteRvItemBinding
import com.leahhumlelu.kotlinprogram.devbyte.domain.Video

class DevByteAdapter: ListAdapter<Video, DevByteAdapter.DevByteViewHolder>(VideoDiff) {

    class DevByteViewHolder(val binding: DevByteRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video){
            binding.video=video
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DevByteRvItemBinding.inflate(layoutInflater)
        return DevByteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        val video = getItem(position)
        holder.bind(video)
    }
}

object VideoDiff: DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.videoUrl==newItem.videoUrl
    }

}


