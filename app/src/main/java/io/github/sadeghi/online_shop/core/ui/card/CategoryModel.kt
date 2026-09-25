package io.github.sadeghi.online_shop.core.ui.card

import io.github.sadeghi.online_shop.R

data class Category(
    val id: Int,
    val image: Int,
    val title: String
)

val categories = listOf(
    Category(1, R.drawable.woman, "زنانه"),
    Category(2, R.drawable.man, "مردانه"),
    Category(3, R.drawable.girl, "دخترانه"),
    Category(4, R.drawable.boy, "پسرانه"),
    Category(5, R.drawable.baby, "نوزادی"),
    Category(6, R.drawable.shoes, "کفش")
)