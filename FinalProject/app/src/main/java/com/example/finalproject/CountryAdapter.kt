package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemCountryBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    inner class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.ISO2 == newItem.ISO2
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        private val differ = AsyncListDiffer(this@CountryAdapter, diffCallback)
        var countries: List<Country>
            get() = differ.currentList
            set(value) { differ.submitList(value) }

        override fun getItemCount() = countries.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
            return CountryViewHolder(ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        }

        override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
            holder.binding.apply {
                val country = countries[position]
                tvTitle.text = country.Country
            }
        }
    }
}