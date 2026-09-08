package io.github.sadeghi.online_shop.ui.screens.mainScreen.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun CustomTopBar(
    showBackButton: Boolean,
    isDrawerOpen: Boolean,
    onMenuClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onBackClick: () -> Unit,
    hasNotification: Boolean
) {

    val viewModel: ProfileViewModel = hiltViewModel()

    val profileImageUri by viewModel.profileImageUri.collectAsState(
        initial = null
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(color = Color.White)
            .height(64.dp)
            .padding(horizontal = 12.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        // فلش بازگشت
        if (showBackButton) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "بازگشت"
                )
            }
        }


        // عکس لوگو
        Image(
            painter = painterResource(R.drawable.fulllogo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        // فاصله
        Spacer(
            modifier = Modifier.weight(1f)
        )

        // پروفایل
        if (profileImageUri != null) {
            AsyncImage(
                model = profileImageUri,
                contentDescription = "پروفایل",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "پروفایل",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }


        // ناتیفیکیشن
        IconButton(
            onClick = onNotificationClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notif),
                contentDescription = "اعلان‌ها",
                tint = if (hasNotification) Color.Unspecified else Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }


        // منوی Drawer
        IconButton(
            onClick = onMenuClick
        ) {
            Icon(
                imageVector = if (isDrawerOpen) {
                    Icons.Default.Close
                } else {
                    Icons.Default.Menu
                },
                contentDescription = if (isDrawerOpen) {
                    "بستن منو"
                } else {
                    "باز کردن منو"
                }
            )
        }


    }
}
