package com.olivares.dogapp.presentation.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olivares.dogapp.R
import com.olivares.dogapp.databinding.ItemImageBinding
import com.squareup.picasso.Picasso

class ImagesAdapter(
    private var images: List<String> = listOf()
) : RecyclerView.Adapter<ImagesAdapter.ItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(items: List<String>) {
        this.images = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        val itemBinding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(parent: ItemViewHolder, position: Int) {
        parent.bind(images[position])
    }

    override fun getItemCount() = images.size

    inner class ItemViewHolder(private val viewBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(urlImage: String) {
            Picasso.get()
                .load(urlImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(viewBinding.ivImage)
        }
    }
}
