package io.github.sadeghi.online_shop.ui.screens.profilescreen.address

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.viewModel.AddressViewModel
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun AddressFormScreen(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel,
    addressId: UUID? = null
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val addresses by viewModel.addresses.collectAsState()


    LaunchedEffect(addressId) {
        if (addressId == null) {
            viewModel.clearForm()
        }
    }

    LaunchedEffect(addressId, addresses) {
        if (addressId != null) {

            addresses
                .find { it.id == addressId }
                ?.let { address ->
                    viewModel.loadAddress(address)
                }
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
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }
        ) {

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
                HeaderProfile(
                    compact = true,
                    profileViewModel = profileViewModel
                )
            }

            SpacerHeight(40)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Text(
                    text = if (addressId == null) {
                        "ثبت آدرس جدید"
                    } else {
                        "ویرایش آدرس"
                    },
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )

                SpacerHeight(20)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                        .verticalScroll(scrollState)
                ) {

                    Text(
                        text = "گیرنده :",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SpacerHeight(10)

                    AppTextField(
                        value = viewModel.fullName,
                        onValueChange = viewModel::onFullNameChange,
                        placeholder = "نام و نام خانوادگی",
                        imeAction = ImeAction.Next,
                        isError = viewModel.fullNameError != null,
                        supportingText = viewModel.fullNameError,
                        onImeAction = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )

                    SpacerHeight(20)

                    Text(
                        text = "آدرس :",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SpacerHeight(10)

                    AppTextField(
                        value = viewModel.address,
                        onValueChange = viewModel::onAddressChange,
                        placeholder = "آدرس کامل خود را وارد کنید",
                        imeAction = ImeAction.Next,
                        isError = viewModel.addressError != null,
                        supportingText = viewModel.addressError,
                        onImeAction = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                    SpacerHeight(20)


                    Text(
                        text = "کد پستی :",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SpacerHeight(10)

                    AppTextField(
                        value = viewModel.postalCode,
                        onValueChange = {
                            if (it.length <= 10) {
                                viewModel.onPostalCodeChange(it)
                            }
                        },
                        placeholder = "کد پستی را وارد کنید",
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                        isError = viewModel.postalCodeError != null,
                        supportingText = viewModel.postalCodeError,
                        onImeAction = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                    SpacerHeight(20)
                    Text(
                        text = "شماره همراه :",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SpacerHeight(10)

                    AppTextField(
                        value = viewModel.phoneNumber,
                        onValueChange = {
                            if (it.length <= 11) {
                                viewModel.onPhoneNumberChange(it)
                            }
                        },
                        placeholder = "*********09",
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done,
                        isError = viewModel.phoneNumberError != null,
                        supportingText = viewModel.phoneNumberError,
                        onImeAction = {
                            focusManager.clearFocus()

                            scope.launch {
                                delay(100.milliseconds)

                                scrollState.animateScrollTo(
                                    scrollState.maxValue
                                )
                            }
                        }
                    )

                    SpacerHeight(25)

                    GradientButton(
                        text = if (addressId == null) {
                            "ثبت آدرس"
                        } else {
                            "ذخیره تغییرات"
                        }
                    ) {

                        if (viewModel.validateForm()) {
                            if (addressId == null) {
                                viewModel.addAddress {
                                    navController.popBackStack()
                                }
                            } else {
                                viewModel.updateAddress(addressId) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    }

                    SpacerHeight(20)
                }
            }
        }
    }
}