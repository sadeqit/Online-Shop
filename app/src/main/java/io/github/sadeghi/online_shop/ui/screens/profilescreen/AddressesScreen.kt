package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.Address
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.AddressCard
import io.github.sadeghi.online_shop.viewModel.AddressViewModel
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun AddressesScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    viewModel: AddressViewModel = hiltViewModel()
) {

    val addresses by viewModel.addresses.collectAsState()

    var addressToDelete by remember {
        mutableStateOf<Address?>(null)
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
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
                    text = "آدرس های من",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )

                SpacerHeight(20)
                if (addresses.isEmpty()) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "هیچ آدرسی ندارید!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        SpacerHeight(8)

                        Text(
                            text = "لطفا یک آدرس اضافه کنید",
                            fontSize = 18.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }

                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {

                        addresses.forEach { address ->

                            AddressCard(
                                address = address,

                                onEditClick = {
                                    navController.navigate(
                                        Screens.AddressFormScreen.createRoute(address.id)
                                    )
                                },

                                onDeleteClick = {
                                    addressToDelete = address
                                },

                                onDefaultChange = {
                                    viewModel.setDefaultAddress(address.id)
                                }
                            )

                            SpacerHeight(20)
                        }
                    }
                }

                GradientButton(
                    text = "+ ثبت آدرس جدید"
                ) {

                    navController.navigate(
                        Screens.AddressFormScreen.route
                    )
                }

                SpacerHeight(20)
            }
        }
        addressToDelete?.let { address ->

            AlertDialog(
                containerColor = Color(0xFFFCF3EC),
                onDismissRequest = {
                    addressToDelete = null
                },
                title = {
                    Text(
                        text = "حذف آدرس",
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )
                },
                text = {
                    Text(
                        text = "آیا از حذف این آدرس مطمئن هستید؟",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteAddress(address.id)
                            addressToDelete = null
                        }
                    ) {
                        Text(
                            text = "حذف",
                            color = Color.Red,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            addressToDelete = null
                        }
                    ) {
                        Text(
                            text = "انصراف",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }

}