package com.example.musicplayer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.musicplayer.R
import com.example.musicplayer.data.entities.Song
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class SwipeSongAdapter :BaseSongAdapter(R.layout.swipe_item) {

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: BaseSongAdapter.SongViewHolder, position: Int) {
        var song = songs[position]
        holder.itemView.apply{
            val text = song.title
            tvPrimary.text = text
        }
    }

}



