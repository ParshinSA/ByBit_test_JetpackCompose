package com.example.bybit_test_project.data.models.annnouncements_dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementsResponse(
    @SerialName("result")
    val result: ResultListDto
)

