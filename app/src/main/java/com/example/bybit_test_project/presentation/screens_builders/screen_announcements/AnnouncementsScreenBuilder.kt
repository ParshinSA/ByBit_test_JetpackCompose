package com.example.bybit_test_project.presentation.screens_builders.screen_announcements

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bybit_test_project.R
import com.example.bybit_test_project.data.models.annnouncements_dto.NewsDto
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenState.CONTINUE
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenState.ERROR
import com.example.bybit_test_project.presentation.screens_builders.screen_announcements.AnnouncementsScreenState.LOADING
import com.example.bybit_test_project.presentation.ui.builders.mockListNews
import com.example.bybit_test_project.presentation.ui.theme.ByBit_test_projectTheme
import kotlinx.coroutines.launch

private const val PADDING_BETWEEN_CONTENT = 4

@Composable
fun AnnouncementsScreenBuilder(
    stateScreen: AnnouncementsScreenState,
    listNews: List<NewsDto>,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = { ThisToolbar() }) { pagingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(pagingValues)
        ) {
            when {
                stateScreen == CONTINUE && listNews.isEmpty() -> AnnouncementsScreenNoContent()
                stateScreen == CONTINUE -> AnnouncementsScreenContent(listNews)
                stateScreen == LOADING -> AnnouncementsScreenLoading()
                stateScreen == ERROR -> AnnouncementsScreenError()
            }
        }
    }
}

@Composable
fun BoxScope.AnnouncementsScreenNoContent() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.image_no_content),
        contentDescription = stringResource(id = R.string.no_content)
    )
    Text(
        modifier = Modifier.align(Alignment.TopCenter),
        text = stringResource(id = R.string.no_content),
        style = MaterialTheme.typography.titleLarge,
        color = Color.White
    )
}

@Composable
fun AnnouncementsScreenError() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.error),
        contentDescription = stringResource(id = R.string.error)
    )
}

@Composable
fun BoxScope.AnnouncementsScreenLoading() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
private fun BoxScope.AnnouncementsScreenContent(listNews: List<NewsDto>) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(PADDING_BETWEEN_CONTENT.dp),
    ) {
        itemsIndexed(listNews) { index, news ->
            if (index > 0) Spacer(modifier = Modifier.size(PADDING_BETWEEN_CONTENT.dp))

            NewsCard(news = news)

            if (index == listNews.lastIndex) {
                val heightScreen = LocalConfiguration.current.screenHeightDp
                Spacer(modifier = Modifier.size((heightScreen / 10).dp))
            }
        }
    }
    ButtonScrollUp(listState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ThisToolbar() {
    TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
        Text(color = Color.White, text = stringResource(id = R.string.announcements))
    })
}

@Composable
private fun BoxScope.ButtonScrollUp(listState: LazyListState) {
    val coroutineScope = rememberCoroutineScope()
    val firstItem by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    Card(
        shape = CircleShape,
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.BottomEnd)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
    ) {
        if (firstItem > 0) {
            SmallFloatingActionButton(
                onClick = { coroutineScope.launch { listState.animateScrollToItem(0) } },
                containerColor = MaterialTheme.colorScheme.secondary,
            ) {
                Icon(
                    modifier = Modifier.rotate(90f),
                    imageVector = Icons.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    contentDescription = stringResource(id = R.string.up)
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 400, heightDp = 700)
fun AnnouncementsScreenContentPreview() {
    ByBit_test_projectTheme { Box { AnnouncementsScreenContent(listNews = mockListNews) } }
}

@Composable
@Preview(widthDp = 400, heightDp = 700)
fun AnnouncementsScreenErrorPreview() {
    ByBit_test_projectTheme { AnnouncementsScreenError() }
}

@Composable
@Preview(widthDp = 400, heightDp = 700)
fun AnnouncementsScreenNoContentPreview() {
    ByBit_test_projectTheme { Box { AnnouncementsScreenNoContent() } }
}