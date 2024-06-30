package ru.melnikov.swapiapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.domain.models.Person

@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    person: Person,
    onItemClick: (Int) -> Unit
) {

    Card(
        modifier = modifier.padding(4.dp),
        onClick = {
            onItemClick(person.homeWorld)
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = person.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.gender),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = person.gender.stringRes),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.birth_year),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = person.birthYear,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}