package com.example.bybit_test_project.presentation.ui.builders

import com.example.bybit_test_project.data.models.annnouncements_dto.NewsDto
import com.example.bybit_test_project.data.models.annnouncements_dto.TypeNewsDto

val mockTypeNews = TypeNewsDto("Latest Activities")

val mockNews = NewsDto(
    title = "Preview",
    description = "Our rejuvenated Copy Trading 101 event is designed to help you safely navigate the markets, minimize potential losses, and optimize your trading strategy with a 100 USDT Loss Coverage Voucher.",
    tags = listOf("Derivatives", "Copy_Trading", "USDT","Derivatives", "Copy_Trading", "USDT","Derivatives", "Copy_Trading", "USDT","Derivatives", "Copy_Trading", "USDT","Derivatives", "Copy_Trading", "USDT","Derivatives", "Copy_Trading", "USDT","Derivatives", "Copy_Trading", "USDT"),
    publicationDate = 1560507488,
    type = mockTypeNews,
    url = "https://announcements.bybit.com/en-US/article/protect-your-first-copy-trade-with-100-usdt-loss-coverage-bltef8ac47a10cb68b5/"
)

val mockListNews = listOf(mockNews, mockNews, mockNews, mockNews, mockNews, mockNews)
