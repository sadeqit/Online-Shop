package io.github.sadeghi.online_shop.ui.screens

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.card.CardItem
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.profileCards
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val profileImageUri by viewModel.profileImageUri.collectAsState(
        initial = null
    )
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        uri?.let {
            val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION

            context.contentResolver.takePersistableUriPermission(
                it,
                takeFlags
            )

            viewModel.saveProfileImage(it.toString())
        }
    }


    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderProfile(
                profileImageUri = profileImageUri,
                onUploadClick = {
                    imagePickerLauncher.launch(
                        arrayOf("image/*")
                    )
                },
                onEditClick = {
                    navController.navigate(Screens.EditProfile.route)
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 20.dp)
            ) {

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 2,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    profileCards.forEach { card ->
                        CardItem(
                            image = card.image,
                            title = card.title,
                            profileStyle = true,
                            modifier = Modifier.size(150.dp, 140.dp),
                            onClick = {
                                card.route.let { route ->
                                    navController.navigate(route)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}