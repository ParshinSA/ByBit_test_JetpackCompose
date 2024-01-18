package com.example.bybit_test_project.data.repositories

import com.example.bybit_test_project.data.models.annnouncements_dto.NewsDto
import com.example.bybit_test_project.data.network.api.ByBitApi
import com.example.bybit_test_project.presentation.dependencies.AnnouncementsRepository

class AnnouncementsRepositoryImpl(
    private val byBitApi: ByBitApi
) : AnnouncementsRepository {

    override suspend fun getAnnouncements(): List<NewsDto> {
        return byBitApi.getAnnouncements().result.list
    }

}