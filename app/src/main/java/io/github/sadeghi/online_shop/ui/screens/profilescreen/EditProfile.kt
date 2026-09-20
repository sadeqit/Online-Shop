package io.github.sadeghi.online_shop.ui.screens.profilescreen

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.yalantis.ucrop.UCrop
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.SubmitContent
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel
import java.io.File

@Composable
fun EditProfileScreen(
    profileViewModel: ProfileViewModel,
    onSaveSuccess: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val cropLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {

            val resultUri = result.data?.let {
                UCrop.getOutput(it)
            }

            resultUri?.let {
                profileViewModel.saveProfileImage(it.toString())
            }
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        uri?.let {

            val destinationUri = Uri.fromFile(
                File(
                    context.cacheDir,
                    "cropped_${System.currentTimeMillis()}.jpg"
                )
            )

            val options = UCrop.Options().apply {
                setCircleDimmedLayer(true)
                setShowCropGrid(false)
            }

            val cropIntent = UCrop.of(
                it,
                destinationUri
            )
                .withAspectRatio(1f, 1f)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .getIntent(context)

            cropLauncher.launch(cropIntent)
        }
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    focusManager.clearFocus()
                }
        ) {

            HeaderProfile(
                showUserInfo = false,
                iconEdit = false,
                onUploadClick = {
                    imagePickerLauncher.launch(
                        arrayOf("image/*")
                    )
                },
                profileViewModel = profileViewModel
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                SpacerHeight(25)

                SubmitContent(
                    viewModel = profileViewModel,
                    focusManager = focusManager
                )

                SpacerHeight(15)

                Box(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    GradientButton(
                        text = "ثبت تغییرات",
                        enabled = !profileViewModel.isSaving
                    ) {
                        if (profileViewModel.validateProfile()) {
                            profileViewModel.saveProfile {
                                onSaveSuccess()
                            }
                        }
                    }
                }

                SpacerHeight(25)
            }
        }
    }
}