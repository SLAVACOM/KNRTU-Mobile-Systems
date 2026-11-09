package com.example.knrtu_mobile_systems.ui

import androidx.lifecycle.ViewModel
import com.example.knrtu_mobile_systems.data.LocalPlacesDataProvider
import com.example.knrtu_mobile_systems.model.Category
import com.example.knrtu_mobile_systems.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * UI state of the My City app.
 */
data class MyCityUiState(
    val categories: List<Category> = Category.entries,
    val currentCategory: Category = Category.Sights,
    val places: List<Place> = LocalPlacesDataProvider.getPlaces(Category.Sights),
    val currentPlace: Place = LocalPlacesDataProvider.getPlaces(Category.Sights).first(),
)

class MyCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun selectCategory(category: Category) {
        val places = LocalPlacesDataProvider.getPlaces(category)
        _uiState.update {
            it.copy(
                currentCategory = category,
                places = places,
                currentPlace = places.first(),
            )
        }
    }

    fun selectPlace(place: Place) {
        _uiState.update { it.copy(currentPlace = place) }
    }
}
