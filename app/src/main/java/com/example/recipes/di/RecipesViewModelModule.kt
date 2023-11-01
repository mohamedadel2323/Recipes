package com.example.recipes.di

import com.example.recipes.data.remote.RecipesClient
import com.example.recipes.data.remote.RemoteSource
import com.example.recipes.data.repository.Repository
import com.example.recipes.domain.repository.RepositoryInterface
import com.example.recipes.domain.usecases.GetAllRecipesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RecipesViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteSource(recipesClient: RecipesClient): RemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindRecipesRepo(repository: Repository): RepositoryInterface

    companion object {
        @Provides
        @ViewModelScoped
        fun provideGetRecipesUseCase(repositoryInterface: RepositoryInterface) =
            GetAllRecipesUseCase(repositoryInterface)
    }
}