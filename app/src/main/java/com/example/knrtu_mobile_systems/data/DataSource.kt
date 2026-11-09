package com.example.knrtu_mobile_systems.data

import com.example.knrtu_mobile_systems.R

object DataSource {
    val flavors: List<Int> = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions: List<Pair<Int, Int>> = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
}