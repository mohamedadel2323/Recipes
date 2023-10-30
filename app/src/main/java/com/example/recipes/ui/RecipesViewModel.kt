package com.example.recipes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.models.RecipeDomainModel
import com.example.recipes.domain.usecases.GetAllRecipesUseCase
import com.example.recipes.ui.mappers.toUiModel
import com.example.recipes.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RecipesViewModel(private val getAllRecipesUseCase: GetAllRecipesUseCase) : ViewModel() {

    private var _homeState = MutableLiveData(HomeState.Display())
    val homeState: LiveData<HomeState.Display>
        get() = _homeState
    private var _errorState: MutableSharedFlow<HomeState.Error> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun senEvent(recipesEvent: RecipesEvent){
        when(recipesEvent){
            is RecipesEvent.GetRecipes ->{
                getAllRecipes()
            }
        }
    }

    private fun getAllRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeState.postValue(_homeState.value?.copy(loading = true))

            getAllRecipesUseCase<List<RecipeDomainModel>>().apply {
                when (this) {
                    is Response.Success -> {
                        data?.let { recipes ->
                            _homeState.postValue(_homeState.value?.copy(recipes = recipes.map { it.toUiModel() }, loading = false))
                        }
                    }
                    is Response.Failure -> {
                        _homeState.postValue(_homeState.value?.copy(loading = false))
                        error?.let { _errorState.emit(HomeState.Error(it)) }
                    }
                }
            }
        }
    }
}

class RecipesViewModelFactory(private val getAllRecipesUseCase: GetAllRecipesUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            RecipesViewModel(getAllRecipesUseCase) as T
        } else {
            throw IllegalArgumentException("View Model class not found")
        }
    }
}