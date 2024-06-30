package ru.melnikov.swapiapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.domain.models.Film

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilmItem(
    modifier: Modifier = Modifier,
    film: Film,
    onFilmClick: (List<Int>, String) -> Unit
) {

    Card(
        modifier = modifier.padding(4.dp),
        onClick = { onFilmClick(film.characters, film.title) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = film.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.director),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = film.director,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.producer),
                style = MaterialTheme.typography.bodySmall
            )
        }
        FlowRow {
            film.producer.forEach { producer ->
                Box(
                    Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = producer,
                        modifier = Modifier.padding(3.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(
                    id = R.string.release_year,
                    film.releaseDate?.year.toString()
                ),
                style = TextStyle(fontFamily = Cursive),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}