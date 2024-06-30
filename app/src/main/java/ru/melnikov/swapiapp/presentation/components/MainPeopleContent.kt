package ru.melnikov.swapiapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import ru.melnikov.swapiapp.domain.models.Person

@Composable
fun MainPeopleContent(
    modifier: Modifier = Modifier,
    onRefreshSwipe: (Boolean) -> Unit,
    swipeRefreshState: SwipeRefreshState,
    people: List<Person>
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
            items(items = people, key = { person -> person.id }) { person ->
                PersonItem(modifier = Modifier.animateItem(), person = person)
            }
        }
    }
}