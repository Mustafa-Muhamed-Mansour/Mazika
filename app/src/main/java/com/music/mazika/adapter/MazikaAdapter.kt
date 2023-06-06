package com.music.mazika.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.music.mazika.databinding.ItemMazikaBinding
import com.music.mazika.interfaces.ClickedOfSong
import com.music.mazika.model.SongModel

class MazikaAdapter(private val song: ClickedOfSong): Adapter<MazikaAdapter.MazikaViewHolder>() {

    private val differCallBack = object : ItemCallback<SongModel>() {
        override fun areItemsTheSame(oldItem: SongModel, newItem: SongModel): Boolean {
            return oldItem.title == newItem.title && oldItem.subTitle == newItem.subTitle && oldItem.songUrl == newItem.songUrl
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SongModel, newItem: SongModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MazikaViewHolder {
        return MazikaViewHolder(ItemMazikaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(holder: MazikaViewHolder, position: Int) {
        differ.currentList[position].apply {
            holder.bind.txtTitleItemMazika.text = this.title + " ( ${this.subTitle } )"
            holder.bind.animationItemMazika.pauseAnimation()

            holder.itemView.setOnClickListener {
                if (holder.bind.checkSongItemMazika.isChecked.not()) {
                    song.checkedOfSongToPlay(holder.bind.animationItemMazika)
                } else {
                    song.clickedOfSongToPlayAndPause(this, holder.bind.animationItemMazika)
                }
            }
        }
    }

    class MazikaViewHolder(binding: ItemMazikaBinding) : ViewHolder(binding.root) {
        val bind = binding
    }
}