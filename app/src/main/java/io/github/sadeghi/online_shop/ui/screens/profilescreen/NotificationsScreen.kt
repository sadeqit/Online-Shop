package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.viewModel.HomeViewModel
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun NotificationsScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileImageUri by profileViewModel.profileImageUri.collectAsState(
        initial = null
    )
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetNotification()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HeaderProfile(
            compact = true,
            profileImageUri = profileImageUri
        )

        SpacerHeight(50)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Text(
                text = "اعلانات من",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(20)


        }
    }
}