package com.example.recipes.di

import com.example.recipes.data.remote.RecipesClient
import com.example.recipes.data.remote.RecipesService
import com.example.recipes.data.remote.RemoteSource
import com.example.recipes.data.repository.Repository
import com.example.recipes.domain.repository.RepositoryInterface
import com.example.recipes.domain.usecases.GetAllRecipesUseCase
import com.example.recipes.ui.RecipesViewModel
import com.example.recipes.ui.RecipesViewModelFactory
import com.example.recipes.utils.Constants
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<RecipesService> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RecipesService::class.java)
    }
    single<RemoteSource> { RecipesClient(recipesService = get()) }
    single<RepositoryInterface> { Repository(recipesClient = get()) }
    single { GetAllRecipesUseCase(repository = get()) }
}

val viewModels = module {
    factory { RecipesViewModelFactory(getAllRecipesUseCase = get()) }
    viewModel { RecipesViewModel(getAllRecipesUseCase = get()) }
}