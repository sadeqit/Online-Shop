package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import io.github.sadeghi.online_shop.ui.theme.orange
import io.github.sadeghi.online_shop.viewModel.AddressViewModel

@Composable
fun AddressContent(
    navController: NavHostController,
    onBack: () -> Unit,
    onContinue: () -> Unit
) {

    val viewModel: AddressViewModel = hiltViewModel()
    var addressToDelete by remember {
        mutableStateOf<Address?>(null)
    }

    val addresses by viewModel.addresses.collectAsState()

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            SpacerHeight(30)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "آدرس های من",
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )

                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "بازگشت"
                    )
                }
            }

            SpacerHeight(20)

            Text(
                text = "آدرس مورد نظر خود را انتخاب کنید.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(20)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(
                    items = addresses,
                    key = { it.id }
                ) { address ->

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
                }

                item {

                    OutlinedButton(
                        onClick = {
                            navController.navigate(
                                Screens.AddressFormScreen.route
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = orange
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = orange
                        )
                    ) {

                        Text(
                            text = "+ ثبت آدرس جدید",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            color = orange
                        )
                    }
                }
                item { SpacerHeight(30) }
            }



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                GradientButton(
                    text = "انتخاب آدرس و ادامه خرید",
                    enabled = addresses.any { it.isDefault },
                    onClick = onContinue
                )
            }

            SpacerHeight(20)
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
}

