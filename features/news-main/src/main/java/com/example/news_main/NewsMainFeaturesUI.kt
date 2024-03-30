package com.example.news_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewsMainScreen(){
    NewsMainScreen(viewModel = viewModel())
}

@Composable
internal fun NewsMainScreen(viewModel: NewsMainViewModel){
    val state by viewModel.state.collectAsState()
    when (val currentState = state){
        is State.Success -> Articles(currentState.articles)
        is State.Error -> ArticlesWithError(currentState.articles)
        is State.Loading -> ArticlesDuringUpdate(currentState.articles)
        is State.None -> NewsEmpty()
    }
}

@Composable
internal fun ArticlesWithError(articles: List<ArticleUI>?) {
    Column {
        Box(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error),
            contentAlignment = Alignment.Center
        ){
            Text("Error during update", color = MaterialTheme.colorScheme.onError)
        }
        if (articles != null){
            Articles(articles = articles)
        }
    }
}

@Composable
@Preview
internal fun ArticlesDuringUpdate(
    @PreviewParameter(ArticlesPreviewProvider::class, limit = 1)   articles: List<ArticleUI>?
) {
    Column {
        Box(Modifier.padding(8.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        if (articles != null){
            Articles(articles = articles)
        }
    }
}

@Composable
fun NewsEmpty() {
    Box(contentAlignment = Alignment.Center){
        Text("No News")
    }
}

@Preview
@Composable
private fun Articles(
    @PreviewParameter(ArticlesPreviewProvider::class, limit = 1)   articles: List<ArticleUI>
){
    LazyColumn {
        items (articles){ article ->
            key(article.id) {
                Article(article)
            }
        }
    }
}
@Preview
@Composable
internal fun Article(
    @PreviewParameter(ArticlePreviewProvider::class, limit = 1) article: ArticleUI
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = article.title, style = MaterialTheme.typography.headlineMedium, maxLines = 1)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = article.description, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
    }
}

private class ArticlePreviewProvider:PreviewParameterProvider<ArticleUI> {
    override val values = sequenceOf(
        ArticleUI(
            1,
            "Android Studio Iguana is Stable",
            "New version is given",
            imageUrl = null,
            url = ""),
        ArticleUI(
            2,
            "New Kazakh AI is realised",
            "Group of Students from SDU create new innovate AI ",
            imageUrl = null,
            url = ""),
        ArticleUI(
            3,
            "App which give right direction new 2gis",
            "App realised by short time",
            imageUrl = null,
            url = ""),
    )
}


private class ArticlesPreviewProvider:PreviewParameterProvider<List<ArticleUI>> {

    private val articleProvider = ArticlePreviewProvider()

    override val values = sequenceOf(
        articleProvider.values
            .toList()
    )
}