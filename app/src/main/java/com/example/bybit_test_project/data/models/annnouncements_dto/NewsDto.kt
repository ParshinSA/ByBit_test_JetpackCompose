package com.example.bybit_test_project.data.models.annnouncements_dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("tags")
    val tags: List<String>,

    @SerialName("dateTimestamp")
    val publicationDate: Long,

    @SerialName("type")
    val type: TypeNewsDto,

    @SerialName("url")
    val url: String,

    )

