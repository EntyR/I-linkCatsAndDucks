package com.enty.ilinkcatsandducks.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.enty.ilinkcatsandducks.data.db.ImageItemModel
import com.enty.ilinkcatsandducks.databinding.ImageItemBinding

class FavoriteAdapter(val context: Context) : ListAdapter<ImageItemModel, FavoriteAdapter.DocViewHolder>(Companion) {

    inner class DocViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<ImageItemModel>() {
        override fun areItemsTheSame(
            oldItem: ImageItemModel,
            newItem: ImageItemModel
        ): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(
            oldItem: ImageItemModel,
            newItem: ImageItemModel
        ): Boolean {
            return oldItem.uri == newItem.uri
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocViewHolder {
        return DocViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DocViewHolder, position: Int) {
        val data = currentList[position]
        val binding = holder.binding
        Glide
            .with(context)
            .load(data.uri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.cause?.printStackTrace()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    binding.ivAnimalItem.visibility = View.VISIBLE
                    binding.itemProgressBar.visibility = View.INVISIBLE
                    binding.ivAnimalItem.setImageDrawable(resource)

                    return false
                }
            })
            .centerCrop()
            .into(binding.ivAnimalItem)
    }


}