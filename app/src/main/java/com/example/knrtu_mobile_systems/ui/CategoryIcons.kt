package com.example.knrtu_mobile_systems.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.TheaterComedy
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.knrtu_mobile_systems.model.Category

/**
 * Icon that represents the [Category] in the UI.
 */
val Category.icon: ImageVector
    get() = when (this) {
        Category.Sights -> Icons.Filled.AccountBalance
        Category.Museums -> Icons.Filled.Museum
        Category.Parks -> Icons.Filled.Park
        Category.Theatres -> Icons.Filled.TheaterComedy
        Category.Food -> Icons.Filled.Restaurant
    }
