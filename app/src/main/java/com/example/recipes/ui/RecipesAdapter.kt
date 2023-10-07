package com.example.recipes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.data.dto.RecipesResponseItem
import com.example.recipes.databinding.RecipeListItemBinding

class RecipesAdapter : ListAdapter<RecipesResponseItem, RecipeViewHolder>(RecipesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = DataBindingUtil.inflate<RecipeListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recipe_list_item,
            parent,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RecipeViewHolder(private val binding: RecipeListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: RecipesResponseItem) {
        binding.apply {
            this.recipe = recipe
            executePendingBindings()
        }
    }
}

class RecipesDiffUtil : DiffUtil.ItemCallback<RecipesResponseItem>() {
    override fun areItemsTheSame(
        oldItem: RecipesResponseItem,
        newItem: RecipesResponseItem
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: RecipesResponseItem,
        newItem: RecipesResponseItem
    ): Boolean {
        return oldItem == newItem
    }
}