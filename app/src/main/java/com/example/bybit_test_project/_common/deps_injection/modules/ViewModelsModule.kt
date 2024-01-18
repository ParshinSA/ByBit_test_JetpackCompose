package com.example.bybit_test_project._common.deps_injection.modules

import androidx.lifecycle.ViewModel
import com.example.bybit_test_project.presentation.screens_builders.viewmodels.AnnouncementsViewModel
import com.example.bybit_test_project.presentation.screens_builders.viewmodels.ViewModelsFactory
import com.example.bybit_test_project.presentation.dependencies.AnnouncementsRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelsModule {

    @Provides
    @Singleton
    fun providesViewModelFactory(
        viewModelMap: Map<Class<*>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelsFactory {
        return ViewModelsFactory(
            viewModelMap = viewModelMap
        )
    }

    @Provides
    @[IntoMap ClassKey(AnnouncementsViewModel::class)]
    fun providesAnnouncementsViewModel(
        repository: AnnouncementsRepository
    ): ViewModel {
        return AnnouncementsViewModel(
            repository = repository
        )
    }

}