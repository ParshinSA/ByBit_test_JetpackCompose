package com.example.bybit_test_project.presentation.dependencies

import com.example.bybit_test_project.data.models.annnouncements_dto.NewsDto

interface AnnouncementsRepository {
    suspend fun getAnnouncements(): List<NewsDto>
}