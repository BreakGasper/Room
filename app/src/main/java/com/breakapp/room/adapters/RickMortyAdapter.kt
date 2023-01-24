package com.breakapp.room.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.breakapp.room.R
import com.breakapp.room.databinding.ItemrickmortyBinding
import com.breakapp.room.retrofit.RickMorty

class RickMortyAdapter(
    private val context: Context, private val tragosList: List<RickMorty>,
    private val itemClickListener: OnTragoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener {
        fun onTragoClick(drink: RickMorty, position: Int)
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

    inner class MainViewHolder(itemView: View) : BaseViewHolder<RickMorty>(itemView) {
        override fun bind(item: RickMorty, position: Int) {
            val binding = ItemrickmortyBinding.bind(itemView)


            binding.tvName.text = item.nombre
            binding.tvStatus.text = item.descrpcion

            itemView.setOnClickListener { itemClickListener.onTragoClick(item,position) }
        }
    }
}