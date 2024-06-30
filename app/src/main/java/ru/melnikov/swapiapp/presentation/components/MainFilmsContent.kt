package ru.melnikov.swapiapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import ru.melnikov.swapiapp.domain.models.Film

@Composable
fun MainFilmsContent(
    modifier: Modifier = Modifier,
    onRefreshSwipe: (Boolean) -> Unit,
    swipeRefreshState: SwipeRefreshState,
    searchTitle: String,
    films: List<Film>
) {

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize(),
        state = swipeRefreshState,
        onRefresh = {
            onRefreshSwipe(true)
        }
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(items = films.filter { film ->
                val keywords = searchTitle.lowercase().split(" ")
                keywords.all { keyword ->
                    film.title.contains(
                        keyword,
                        ignoreCase = true
                    )
                }
            }, key = { film -> film.id }) { film ->
                FilmItem(
                    modifier = Modifier.animateItem(),
                    film = film,
                    onFilmClick = { _, _ -> }
                )
            }
        }
    }
}