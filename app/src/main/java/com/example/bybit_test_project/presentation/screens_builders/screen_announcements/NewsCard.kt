package com.example.bybit_test_project.presentation.screens_builders.screen_announcements

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bybit_test_project.R
import com.example.bybit_test_project.data.models.annnouncements_dto.NewsDto
import com.example.bybit_test_project.data.models.annnouncements_dto.TypeNewsDto
import com.example.bybit_test_project.presentation.ui.builders.mockNews
import com.example.bybit_test_project.presentation.ui.theme.ByBit_test_projectTheme
import java.text.SimpleDateFormat

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    news: NewsDto,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            val isExpandedInfo = rememberSaveable { mutableStateOf(false) }

            Row {
                Title(news.title)
                MangeDescriptionBtn(isExpandedInfo) {}
            }

            if (isExpandedInfo.value) {
                Description(news.description)
                Tags(news.tags)
                UrlToNetwork(news.url)
                Spacer(modifier = Modifier.size(8.dp))
            }

            Row {
                PublicationDate(news.publicationDate)
                TypeNews(news.type)
            }
        }
    }
}

@Composable
private fun UrlToNetwork(url: String) {
    val context = LocalContext.current

    val openingUrl = { url: String ->
        val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browseIntent)
    }

    Text(
        modifier = Modifier.clickable { openingUrl(url) },
        text = stringResource(id = R.string.more_details),
        textDecoration = TextDecoration.Underline,
        style = MaterialTheme.typography.labelSmall,
        color = Color(0xFF1FA5F7)
    )

}

@Composable
private fun TypeNews(type: TypeNewsDto) {
    Text(
        text = type.title,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
@SuppressLint("SimpleDateFormat")
private fun RowScope.PublicationDate(dateTimeStamp: Long) {

    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")

    Text(
        modifier = Modifier.weight(1f),
        text = simpleDateFormat.format(dateTimeStamp),
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
private fun RowScope.MangeDescriptionBtn(
    isExpandedInfo: MutableState<Boolean>,
    onClick: () -> Unit
) {
    val angle by animateFloatAsState(
        targetValue = if (isExpandedInfo.value) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "",
    )

    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .rotate(angle)
            .align(Alignment.CenterVertically)
            .clickable {
                onClick.invoke()
                isExpandedInfo.value = !isExpandedInfo.value
            },
        imageVector = Icons.Filled.KeyboardArrowDown,
        contentDescription = null,
    )
}

@Composable
private fun RowScope.Title(title: String) {
    Text(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .weight(1f),
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = Color.White
    )
}

@Composable
private fun Description(description: String) {
    Text(
        text = description, color = Color.White,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun Tags(tags: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = tags.joinToString(prefix = "#", separator = "#"),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
fun NewsCardPreview() {
    ByBit_test_projectTheme {
        NewsCard(news = mockNews)
    }
}
