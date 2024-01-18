package com.example.bybit_test_project.data.network.api

import com.example.bybit_test_project.data.models.annnouncements_dto.AnnouncementsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class ByBitApi(
    private val client: HttpClient
) {

    suspend fun getAnnouncements(page: Int = 1, limit: Int = 20): AnnouncementsResponse {
        return client.use {
            it.get {
                url {
                    parameters.append(name = "limit", value = limit.toString())
                    parameters.append(name = "page", value = page.toString())
                    parameters.append(name = "locale", value = "en-US")
                    path("/v5/announcements/index")
                }
            }.body<AnnouncementsResponse>()
        }
    }

}