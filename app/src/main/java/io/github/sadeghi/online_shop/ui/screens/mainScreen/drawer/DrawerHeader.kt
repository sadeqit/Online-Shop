package io.github.sadeghi.online_shop.ui.screens.mainScreen.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.ui.component.SpacerWidth
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun DrawerHeader() {

    val viewModel: ProfileViewModel = hiltViewModel()

    val profileImageUri by viewModel.profileImageUri.collectAsState(
        initial = null
    )

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFF0E6),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // سمت راست: عکس + اسم
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (profileImageUri != null) {

                    AsyncImage(
                        model = profileImageUri,
                        contentDescription = "پروفایل",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )

                } else {

                    Image(
                        painter = painterResource(
                            id = R.drawable.profile
                        ),
                        contentDescription = "پروفایل",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                }

                SpacerWidth(12)

                Text(
                    text = "بهرام افشاری",
                    fontWeight = FontWeight.Bold
                )
            }

            // سمت چپ: شماره
            Text(
                text = "09123456789",
                fontSize = 12.sp
            )
        }
    }
}