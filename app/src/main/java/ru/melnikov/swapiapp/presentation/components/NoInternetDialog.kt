package ru.melnikov.swapiapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.plusmobileapps.konnectivity.Konnectivity
import com.plusmobileapps.konnectivity.NetworkConnection
import ru.melnikov.swapiapp.R

@Composable
fun NoInternetDialog() {

    val connection = remember {
        Konnectivity()
    }

    val connectionStatus by connection.currentNetworkConnectionState.collectAsState()

    AnimatedVisibility(visible = connectionStatus == NetworkConnection.NONE) {
        Dialog(properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ), onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .size(400.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_internet),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center
                )
                Text(
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 24.dp),
                    text = stringResource(id = R.string.no_internet),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

}