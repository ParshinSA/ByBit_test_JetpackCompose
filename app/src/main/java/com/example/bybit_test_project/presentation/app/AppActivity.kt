package com.example.bybit_test_project.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.bybit_test_project.presentation.screens_builders.viewmodels.AnnouncementsViewModel
import com.example.bybit_test_project.presentation.screens_builders.viewmodels.ViewModelsFactory
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenBuilder
import com.example.bybit_test_project.presentation.ui.theme.ByBit_test_projectTheme
import javax.inject.Inject

class AppActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        setContent {
            ByBit_test_projectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnnouncementsScreen()
                }
            }
        }
    }

    private fun inject() {
        (applicationContext as? AppApplication)?.component?.inject(this)
    }

    @Composable
    private fun AnnouncementsScreen() {
        val viewModel by viewModels<AnnouncementsViewModel> { viewModelsFactory }

        val listNews by viewModel.listNewsStateFlow.collectAsState()
        val stateScreen by viewModel.stateScreenStateFlow.collectAsState()

        AnnouncementsScreenBuilder(
            stateScreen = stateScreen,
            listNews = listNews,
        )
    }
}
