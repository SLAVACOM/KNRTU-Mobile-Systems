package com.example.knrtu_mobile_systems.model

import androidx.annotation.StringRes

/**
 * A recommended place in the city.
 */
data class Place(
    val id: Int,
    val category: Category,
    @StringRes val name: Int,
    @StringRes val location: Int,
    @StringRes val description: Int,
)
