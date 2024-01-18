package com.example.bybit_test_project.presentation.screens_builders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class ViewModelsFactory(
    private val viewModelMap: Map<Class<*>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
	  return viewModelMap.getValue(modelClass as Class<*>).get() as T
    }
}