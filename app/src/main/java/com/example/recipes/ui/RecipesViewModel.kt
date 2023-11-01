package com.example.recipes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.models.RecipeDomainModel
import com.example.recipes.domain.usecases.GetAllRecipesUseCase
import com.example.recipes.ui.mappers.toUiModel
import com.example.recipes.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val getAllRecipesUseCase: GetAllRecipesUseCase) : ViewModel() {

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