package com.example.knrtu_mobile_systems.model

import androidx.annotation.StringRes
import com.example.knrtu_mobile_systems.R

/**
 * Categories of recommendations in the city.
 */
enum class Category(@StringRes val title: Int) {
    Sights(R.string.category_sights),
    Museums(R.string.category_museums),
    Parks(R.string.category_parks),
    Theatres(R.string.category_theatres),
    Food(R.string.category_food),
}
