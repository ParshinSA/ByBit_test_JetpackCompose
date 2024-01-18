package com.example.bybit_test_project.presentation.screens_builders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bybit_test_project.data.models.annnouncements_dto.NewsDto
import com.example.bybit_test_project.presentation.dependencies.AnnouncementsRepository
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenState.CONTINUE
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenState.ERROR
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenState.LOADING
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnnouncementsViewModel(
    private val repository: AnnouncementsRepository,
) : ViewModel() {

    private val listNewsStateFlowMutStateFlow = MutableStateFlow<List<NewsDto>>(emptyList())
    val listNewsStateFlow = listNewsStateFlowMutStateFlow.asStateFlow()

    private val stateScreenMutStateFlow = MutableStateFlow(LOADING)
    val stateScreenStateFlow = stateScreenMutStateFlow.asStateFlow()

    init {
        updateAnnouncements()
    }

    private fun updateAnnouncements() {
        viewModelScope.launch {
            try {
                stateScreenMutStateFlow.value = LOADING
                listNewsStateFlowMutStateFlow.value = repository.getAnnouncements()
                stateScreenMutStateFlow.value = CONTINUE
            } catch (e: Exception) {
                stateScreenMutStateFlow.value = ERROR
                e.printStackTrace()
            } finally {
                stateScreenMutStateFlow.value = CONTINUE
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}