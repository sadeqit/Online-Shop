package io.github.sadeghi.online_shop.ui.screens.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.SpacerHeight

@Composable
fun SearchBar(
    text : String
) {

    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
               ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text ,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Bold
            )

            SpacerHeight(12)

            AppTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "هرچی میخوای جستجوکن...",
                leadingIcon = {
                    Icon(
                        Icons.Default.Search, "",
                        tint = Color(0xFFEF472C)
                    )
                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                onImeAction = {
                    focusManager.clearFocus()
                    performSearch(searchQuery)
                }
            )
        }
    }
}

fun performSearch(query: String) {
    // اجرای جستجو
    println("جستجو برای: $query")
}