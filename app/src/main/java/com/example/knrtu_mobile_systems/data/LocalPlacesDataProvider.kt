package com.example.knrtu_mobile_systems.data

import com.example.knrtu_mobile_systems.R
import com.example.knrtu_mobile_systems.model.Category
import com.example.knrtu_mobile_systems.model.Place

/**
 * Static data source with recommendations for Kazan.
 */
object LocalPlacesDataProvider {

    val allPlaces: List<Place> = listOf(
        Place(
            id = 1,
            category = Category.Sights,
            name = R.string.place_kremlin_name,
            location = R.string.place_kremlin_location,
            description = R.string.place_kremlin_description,
        ),
        Place(
            id = 2,
            category = Category.Sights,
            name = R.string.place_bauman_name,
            location = R.string.place_bauman_location,
            description = R.string.place_bauman_description,
        ),
        Place(
            id = 3,
            category = Category.Sights,
            name = R.string.place_old_tatar_sloboda_name,
            location = R.string.place_old_tatar_sloboda_location,
            description = R.string.place_old_tatar_sloboda_description,
        ),
        Place(
            id = 4,
            category = Category.Sights,
            name = R.string.place_temple_name,
            location = R.string.place_temple_location,
            description = R.string.place_temple_description,
        ),
        Place(
            id = 5,
            category = Category.Museums,
            name = R.string.place_national_museum_name,
            location = R.string.place_national_museum_location,
            description = R.string.place_national_museum_description,
        ),
        Place(
            id = 6,
            category = Category.Museums,
            name = R.string.place_hermitage_name,
            location = R.string.place_hermitage_location,
            description = R.string.place_hermitage_description,
        ),
        Place(
            id = 7,
            category = Category.Museums,
            name = R.string.place_natural_history_name,
            location = R.string.place_natural_history_location,
            description = R.string.place_natural_history_description,
        ),
        Place(
            id = 8,
            category = Category.Museums,
            name = R.string.place_chak_chak_name,
            location = R.string.place_chak_chak_location,
            description = R.string.place_chak_chak_description,
        ),
        Place(
            id = 9,
            category = Category.Parks,
            name = R.string.place_kremlin_embankment_name,
            location = R.string.place_kremlin_embankment_location,
            description = R.string.place_kremlin_embankment_description,
        ),
        Place(
            id = 10,
            category = Category.Parks,
            name = R.string.place_black_lake_name,
            location = R.string.place_black_lake_location,
            description = R.string.place_black_lake_description,
        ),
        Place(
            id = 11,
            category = Category.Parks,
            name = R.string.place_millennium_name,
            location = R.string.place_millennium_location,
            description = R.string.place_millennium_description,
        ),
        Place(
            id = 12,
            category = Category.Parks,
            name = R.string.place_kaban_embankment_name,
            location = R.string.place_kaban_embankment_location,
            description = R.string.place_kaban_embankment_description,
        ),
        Place(
            id = 13,
            category = Category.Theatres,
            name = R.string.place_opera_name,
            location = R.string.place_opera_location,
            description = R.string.place_opera_description,
        ),
        Place(
            id = 14,
            category = Category.Theatres,
            name = R.string.place_kamal_name,
            location = R.string.place_kamal_location,
            description = R.string.place_kamal_description,
        ),
        Place(
            id = 15,
            category = Category.Theatres,
            name = R.string.place_kachalov_name,
            location = R.string.place_kachalov_location,
            description = R.string.place_kachalov_description,
        ),
        Place(
            id = 16,
            category = Category.Theatres,
            name = R.string.place_ekiyat_name,
            location = R.string.place_ekiyat_location,
            description = R.string.place_ekiyat_description,
        ),
        Place(
            id = 17,
            category = Category.Food,
            name = R.string.place_tatar_cuisine_house_name,
            location = R.string.place_tatar_cuisine_house_location,
            description = R.string.place_tatar_cuisine_house_description,
        ),
        Place(
            id = 18,
            category = Category.Food,
            name = R.string.place_tatar_usadba_name,
            location = R.string.place_tatar_usadba_location,
            description = R.string.place_tatar_usadba_description,
        ),
        Place(
            id = 19,
            category = Category.Food,
            name = R.string.place_tubeteyka_name,
            location = R.string.place_tubeteyka_location,
            description = R.string.place_tubeteyka_description,
        ),
        Place(
            id = 20,
            category = Category.Food,
            name = R.string.place_market_name,
            location = R.string.place_market_location,
            description = R.string.place_market_description,
        ),
    )

    fun getPlaces(category: Category): List<Place> = allPlaces.filter { it.category == category }
}
