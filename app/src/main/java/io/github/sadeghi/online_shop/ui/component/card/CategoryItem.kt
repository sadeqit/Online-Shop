package io.github.sadeghi.online_shop.ui.component.card

import io.github.sadeghi.online_shop.R

data class CategoryItem(
    val id: Int,
    val title: String,
    val image: Int
)

val menItems = listOf(
    CategoryItem(3, "راحتی",R.drawable.tshirt),
    CategoryItem(2,"تیشرت و پولیور",R.drawable.pants ),
    CategoryItem(1, "پیراهن و بلوز",R.drawable.shirt),
    CategoryItem(6, "کیف",R.drawable.accessory),
    CategoryItem(5 ,"جوراب",R.drawable.sweatshirt),
    CategoryItem(4, "شلوار و شلوارک",R.drawable.jacket)
)