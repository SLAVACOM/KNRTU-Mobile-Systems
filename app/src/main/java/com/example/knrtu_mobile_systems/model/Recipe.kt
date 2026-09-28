package com.example.knrtu_mobile_systems.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.RamenDining
import androidx.compose.material.icons.filled.RiceBowl
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.knrtu_mobile_systems.R
import com.example.knrtu_mobile_systems.ui.theme.CategoryBaking
import com.example.knrtu_mobile_systems.ui.theme.CategoryBreakfast
import com.example.knrtu_mobile_systems.ui.theme.CategoryDessert
import com.example.knrtu_mobile_systems.ui.theme.CategoryFastfood
import com.example.knrtu_mobile_systems.ui.theme.CategoryMain
import com.example.knrtu_mobile_systems.ui.theme.CategoryPasta
import com.example.knrtu_mobile_systems.ui.theme.CategorySalad
import com.example.knrtu_mobile_systems.ui.theme.CategorySoup

enum class RecipeCategory(
    @StringRes val labelResId: Int,
    val icon: ImageVector,
    val color: Color
) {
    PASTA(R.string.category_pasta, Icons.Filled.RamenDining, CategoryPasta),
    MAIN(R.string.category_main, Icons.Filled.DinnerDining, CategoryMain),
    SOUP(R.string.category_soup, Icons.Filled.RiceBowl, CategorySoup),
    BAKING(R.string.category_baking, Icons.Filled.BakeryDining, CategoryBaking),
    SALAD(R.string.category_salad, Icons.Filled.Eco, CategorySalad),
    BREAKFAST(R.string.category_breakfast, Icons.Filled.FreeBreakfast, CategoryBreakfast),
    DESSERT(R.string.category_dessert, Icons.Filled.Cake, CategoryDessert),
    FASTFOOD(R.string.category_fastfood, Icons.Filled.Fastfood, CategoryFastfood),
}

data class Recipe(
    val day: Int,
    @StringRes val nameResId: Int,
    @StringRes val descriptionResId: Int,
    val category: RecipeCategory
)
