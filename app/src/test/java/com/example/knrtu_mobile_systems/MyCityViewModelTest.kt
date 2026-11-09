package com.example.knrtu_mobile_systems

import com.example.knrtu_mobile_systems.data.LocalPlacesDataProvider
import com.example.knrtu_mobile_systems.model.Category
import com.example.knrtu_mobile_systems.ui.MyCityViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MyCityViewModelTest {

    private val viewModel = MyCityViewModel()

    @Test
    fun myCityViewModel_initialState_showsFirstSight() {
        val state = viewModel.uiState.value
        assertEquals(Category.Sights, state.currentCategory)
        assertEquals(LocalPlacesDataProvider.getPlaces(Category.Sights).first(), state.currentPlace)
    }

    @Test
    fun myCityViewModel_selectCategory_updatesPlacesAndSelectsFirst() {
        viewModel.selectCategory(Category.Theatres)
        val state = viewModel.uiState.value
        assertEquals(Category.Theatres, state.currentCategory)
        assertTrue(state.places.all { it.category == Category.Theatres })
        assertEquals(state.places.first(), state.currentPlace)
    }

    @Test
    fun myCityViewModel_selectPlace_updatesCurrentPlace() {
        val place = viewModel.uiState.value.places.last()
        viewModel.selectPlace(place)
        assertEquals(place, viewModel.uiState.value.currentPlace)
    }

    @Test
    fun localPlacesDataProvider_everyCategoryHasAtLeastThreePlaces() {
        Category.entries.forEach { category ->
            assertTrue(LocalPlacesDataProvider.getPlaces(category).size >= 3)
        }
    }
}
