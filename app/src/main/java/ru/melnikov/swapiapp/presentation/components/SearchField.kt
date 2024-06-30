package ru.melnikov.swapiapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.melnikov.swapiapp.R

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchName: String,
    onSearchChange: (String) -> Unit,
    onClearClick: () -> Unit,
    placeHolder: String = "",
    focusManager: FocusManager,
    imeAction: ImeAction = ImeAction.Done,
    errorText: String = "",
    keyboardController: SoftwareKeyboardController?,
    enabled: Boolean
) {

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant,
                MaterialTheme.shapes.large
            )
            .clip(MaterialTheme.shapes.large),
        value = searchName,
        onValueChange = {
            onSearchChange(it)
        },
        enabled = enabled,
        isError = errorText.isNotEmpty(),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        placeholder = {
            Text(
                text = errorText.ifEmpty { placeHolder },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                onClearClick()
            }, enabled = searchName.isNotBlank()) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = if (searchName.isNotBlank()) painterResource(id = R.drawable.baseline_close_24) else painterResource(
                        id = R.drawable.baseline_search_24
                    ),
                    contentDescription = "sent",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}