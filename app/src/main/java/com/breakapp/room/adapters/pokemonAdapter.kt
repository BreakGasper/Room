package com.breakapp.room.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breakapp.room.R
import com.breakapp.room.databinding.ItemrickmortyBinding
import com.breakapp.room.retrofit.Pokemon
import com.breakapp.room.retrofit.RickMorty

class pokemonAdapter (
    private val context: Context,
    private val tragosList: List<Pokemon>,
    private val itemClickListener: OnTragoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener {
        fun onTragoClick(drink: Pokemon, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.itemrickmorty, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Pokemon>(itemView) {
        override fun bind(item: Pokemon, position: Int) {
            val binding = ItemrickmortyBinding.bind(itemView)


            binding.tvName.text = item.name
            binding.tvStatus.text = item.url

            itemView.setOnClickListener { itemClickListener.onTragoClick(item,position) }
        }
    }
}