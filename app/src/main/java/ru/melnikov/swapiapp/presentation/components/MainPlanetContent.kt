package ru.melnikov.swapiapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.domain.models.Planet

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainPlanetContent(
    modifier: Modifier = Modifier,
    planet: Planet
) {

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.planet_20),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.BottomEnd,
                alpha = 0.5f
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = planet.name,
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
                        text = stringResource(id = R.string.diameter),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = planet.diameter.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.gravity),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = planet.gravity,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.population),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = planet.population,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.terrain),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                FlowRow {
                    planet.terrain.forEach { terrain ->
                        Box(
                            Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = terrain,
                                modifier = Modifier.padding(3.dp),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.climate),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                FlowRow {
                    planet.climate.forEach { climate ->
                        Box(
                            Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = climate,
                                modifier = Modifier.padding(3.dp),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}