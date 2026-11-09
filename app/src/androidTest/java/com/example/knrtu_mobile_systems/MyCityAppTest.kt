package com.example.knrtu_mobile_systems

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.knrtu_mobile_systems.data.LocalPlacesDataProvider
import com.example.knrtu_mobile_systems.model.Category
import com.example.knrtu_mobile_systems.ui.MyCityApp
import com.example.knrtu_mobile_systems.ui.MyCityScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MyCityAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    private fun setContent(windowSize: WindowWidthSizeClass) {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MyCityApp(windowSize = windowSize, navController = navController)
        }
    }

    private fun string(@StringRes id: Int) = composeTestRule.activity.getString(id)

    private fun assertCurrentRoute(screen: MyCityScreen) {
        assertEquals(screen.name, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun compactDevice_startsOnCategoriesWithoutBackButton() {
        setContent(WindowWidthSizeClass.Compact)
        assertCurrentRoute(MyCityScreen.Categories)
        composeTestRule.onNodeWithText(string(R.string.app_name)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(string(R.string.back_button))
            .assertDoesNotExist()
    }

    @Test
    fun compactDevice_navigatesCategoryToPlaceToDetailsAndBack() {
        setContent(WindowWidthSizeClass.Compact)
        val place = LocalPlacesDataProvider.getPlaces(Category.Museums).last()

        composeTestRule.onNodeWithText(string(Category.Museums.title)).performClick()
        assertCurrentRoute(MyCityScreen.Places)

        composeTestRule.onNodeWithText(string(place.name)).performClick()
        assertCurrentRoute(MyCityScreen.Details)
        composeTestRule.onNodeWithText(string(place.description)).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(string(R.string.back_button)).performClick()
        assertCurrentRoute(MyCityScreen.Places)
        composeTestRule.onNodeWithContentDescription(string(R.string.back_button)).performClick()
        assertCurrentRoute(MyCityScreen.Categories)
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        setContent(WindowWidthSizeClass.Medium)
        composeTestRule.onNodeWithTag(string(R.string.navigation_rail)).assertExists()
        composeTestRule.onNodeWithTag(string(R.string.navigation_drawer)).assertDoesNotExist()
    }

    @Test
    fun mediumDevice_railSelectsCategoryAndShowsItsPlaces() {
        setContent(WindowWidthSizeClass.Medium)
        val place = LocalPlacesDataProvider.getPlaces(Category.Theatres).first()

        composeTestRule.onNodeWithContentDescription(string(Category.Theatres.title)).performClick()
        composeTestRule.onNodeWithText(string(place.name)).assertIsDisplayed()

        composeTestRule.onNodeWithText(string(place.name)).performClick()
        assertCurrentRoute(MyCityScreen.Details)
    }

    @Test
    fun expandedDevice_verifyUsingNavigationDrawerWithDetails() {
        setContent(WindowWidthSizeClass.Expanded)
        composeTestRule.onNodeWithTag(string(R.string.navigation_drawer)).assertExists()
        composeTestRule.onNodeWithTag(string(R.string.navigation_rail)).assertDoesNotExist()
        composeTestRule.onNodeWithTag(string(R.string.details_screen)).assertExists()
    }

    @Test
    fun expandedDevice_clickPlace_showsDetailsWithoutNavigation() {
        setContent(WindowWidthSizeClass.Expanded)
        val place = LocalPlacesDataProvider.getPlaces(Category.Sights)[1]

        composeTestRule.onNodeWithText(string(place.name)).performClick()
        composeTestRule.onNodeWithText(string(place.description)).assertExists()
        assertCurrentRoute(MyCityScreen.Categories)
    }
}
