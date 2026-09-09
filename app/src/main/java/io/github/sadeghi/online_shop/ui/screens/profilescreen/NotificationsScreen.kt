package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.Notification
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.NotificationsViewModel

@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel
) {


    val hasReadNotifications = viewModel.notifications.any { it.isRead }

    val hasUnreadNotifications = viewModel.notifications.any { !it.isRead }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
                HeaderProfile(true)
            }

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

                Row(

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SquareRadioButton(
                            selected = hasUnreadNotifications,
                            onClick = {

                            }
                        )
                        SpacerWidth(3)

                        Text(
                            text = "خوانده نشده",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                    }

                    SpacerWidth(24)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SquareRadioButton(
                            selected = hasReadNotifications,
                            onClick = {

                            }
                        )
                        SpacerWidth(3)
                        Text(
                            text = "خوانده شده",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                    }


                }


                SpacerHeight(10)

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {

                    viewModel.notifications.forEach { notification ->

                        NotifMessage(
                            notification = notification,
                            onRead = {
                                viewModel.markAsRead(notification.id)
                            }
                        )

                        SpacerHeight(12)
                    }
                }


            }
        }
    }
}

@Composable
private fun NotifMessage(
    notification: Notification,
    onRead: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(R.drawable.mail),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(40.dp)
                )
                SpacerWidth(12)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp),
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = notification.subject,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        fontWeight = if (notification.isRead) {
                            FontWeight.Normal
                        } else {
                            FontWeight.Bold
                        },
                        textAlign = TextAlign.Right
                    )


                    SpacerHeight(8)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "غرفه دار",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = notification.date,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }


            }

            SpacerHeight(16)

            ExpandedCard(
                notification = notification,
                expanded = expanded,
                onExpandClick = {
                    expanded = !expanded

                    if (!notification.isRead) {
                        onRead()
                    }
                }
            )
        }
    }
}

@Composable
private fun ExpandedCard(
    notification: Notification,
    expanded: Boolean,
    onExpandClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFCF3EC),
                    shape = RoundedCornerShape(20.dp)
                )
                .then(
                    if (!notification.isRead) {
                        Modifier.border(
                            width = 1.dp,
                            color = Color(0xFFEF472C),
                            shape = RoundedCornerShape(20.dp)
                        )
                    } else {
                        Modifier
                    }
                )
                .clickable {
                    onExpandClick()
                }
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "مشاهده اعلان",
                    fontSize = 14.sp,
                    fontWeight = if (notification.isRead) {
                        FontWeight.Normal
                    } else {
                        FontWeight.Bold
                    }
                )

                SpacerWidth(6)

                Icon(
                    imageVector = if (expanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            if (expanded) {

                SpacerHeight(10)

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    thickness = 1.dp,
                    color = Color(0xFFBDBDBD)
                )

                SpacerHeight(12)

                Text(
                    text = notification.message,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}


@Composable
private fun SquareRadioButton(
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 2.dp,
                color = if (selected) {
                    Color(0xFF08B523)
                } else {
                    Color.Black
                },
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                if (selected) {
                    Color(0xFF08B523)
                } else {
                    Color.Transparent
                }
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

