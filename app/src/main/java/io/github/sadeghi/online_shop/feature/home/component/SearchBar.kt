package io.github.sadeghi.online_shop.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.core.ui.component.AppTextField
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.core.theme.orange

@Composable
fun SearchBar(
    text: String,
    onSearch: (String) -> Unit
) {
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Bold
            )

            SpacerHeight(12)

            AppTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                placeholder = "هرچی میخوای جستجو کن...",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = orange
                    )
                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                onImeAction = {

                    val query = searchQuery.trim()

                    if (query.isNotEmpty()) {
                        focusManager.clearFocus()
                        onSearch(query)
                    }
                }
            )
        }
    }
}
