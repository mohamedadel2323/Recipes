package com.example.recipes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.dto.RecipesResponse
import com.example.recipes.data.repository.RepositoryInterface
import com.example.recipes.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private var _homeState = MutableLiveData(HomeState())
    val homeState: LiveData<HomeState>
        get() = _homeState

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeState.postValue(_homeState.value?.copy(loading = true))
            repository.getAllRecipes<RecipesResponse>().collectLatest {
                when (it) {
                    is Response.Success -> {
                        _homeState.postValue(it.data?.let { recipesResponse ->
                            _homeState.value?.copy(
                                loading = false,
                                recipes = recipesResponse
                            ) ?: HomeState(recipes = listOf())
                        })
                    }

                    is Response.Failure -> {
                        _homeState.postValue(it.error?.let { error ->
                            _homeState.value?.copy(
                                loading = false,
                                error = error
                            ) ?: HomeState(recipes = listOf())
                        })
                    }
                    else -> {}
                }
            }
        }
    }
}
class RecipesViewModelFactory(private val repository: RepositoryInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass::class.java.isInstance(RecipesViewModel::class.java)) {
            RecipesViewModel(repository) as T
        } else {
            throw IllegalArgumentException("View Model class not found")
        }
    }
}