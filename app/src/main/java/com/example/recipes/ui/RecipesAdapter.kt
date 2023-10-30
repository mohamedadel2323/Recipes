package com.example.recipes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.databinding.RecipeListItemBinding
import com.example.recipes.ui.models.RecipeUiModel

class RecipesAdapter : ListAdapter<RecipeUiModel, RecipeViewHolder>(RecipesDiffUtil()) {
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

    fun bind(recipe: RecipeUiModel) {
        binding.apply {
            this.recipe = recipe
            executePendingBindings()
        }
    }
}

class RecipesDiffUtil : DiffUtil.ItemCallback<RecipeUiModel>() {
    override fun areItemsTheSame(oldItem: RecipeUiModel, newItem: RecipeUiModel) =
        oldItem === newItem


    override fun areContentsTheSame(oldItem: RecipeUiModel, newItem: RecipeUiModel) =
        oldItem == newItem
}