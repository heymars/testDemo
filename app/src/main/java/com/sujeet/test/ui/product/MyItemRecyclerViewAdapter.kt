package com.sujeet.test.ui.product

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sujeet.test.R

import com.sujeet.test.ui.product.placeholder.PlaceholderContent.PlaceholderItem
import com.sujeet.test.databinding.FragmentProductBinding
import com.sujeet.test.model.ProductItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<ProductItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(holder.itemView.context).load(item.thumbnail).into(holder.ivProductImage)
        holder.tvTitle.text = item.title
        holder.tvPrice.text = item.price.toString()
        holder.tvRating.text = item.rating.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivProductImage: ImageView = binding.ivProductImage
        val tvTitle: TextView = binding.tvTitle
        val tvPrice: TextView = binding.tvPrice
        val tvRating: TextView = binding.tvRating

        override fun toString(): String {
            return super.toString() + " '" + "'"
        }
    }

}