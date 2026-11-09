package com.example.knrtu_mobile_systems

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.knrtu_mobile_systems.datasource.DataSource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupLunchTrayNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayApp(navController = navController)
        }
    }

    @Test
    fun lunchTrayNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_verifyBackNavigationNotShownOnStartScreen() {
        onNodeWithStringId(R.string.back_button, contentDescription = true).assertDoesNotExist()
    }

    @Test
    fun lunchTrayNavHost_clickStartOrder_navigatesToEntreeScreen() {
        onNodeWithStringId(R.string.start_order).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Entree.name)
    }

    @Test
    fun lunchTrayNavHost_fullOrder_navigatesThroughAllScreens() {
        navigateToCheckoutScreen()
        navController.assertCurrentRouteName(LunchTrayScreen.Checkout.name)
    }

    @Test
    fun lunchTrayNavHost_clickBackOnSideDishScreen_navigatesToEntreeScreen() {
        navigateToSideDishScreen()
        onNodeWithStringId(R.string.back_button, contentDescription = true).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Entree.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelOnAccompanimentScreen_navigatesToStartScreen() {
        navigateToAccompanimentScreen()
        onNodeWithStringId(R.string.cancel).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickSubmitOnCheckoutScreen_navigatesToStartScreen() {
        navigateToCheckoutScreen()
        onNodeWithStringId(R.string.submit).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    private fun navigateToSideDishScreen() {
        onNodeWithStringId(R.string.start_order).performClick()
        composeTestRule.onNodeWithText(DataSource.entreeMenuItems[0].name).performClick()
        onNodeWithStringId(R.string.next).performClick()
    }

    private fun navigateToAccompanimentScreen() {
        navigateToSideDishScreen()
        composeTestRule.onNodeWithText(DataSource.sideDishMenuItems[0].name).performClick()
        onNodeWithStringId(R.string.next).performClick()
    }

    private fun navigateToCheckoutScreen() {
        navigateToAccompanimentScreen()
        composeTestRule.onNodeWithText(DataSource.accompanimentMenuItems[0].name).performClick()
        onNodeWithStringId(R.string.next).performClick()
    }

    private fun onNodeWithStringId(
        @StringRes id: Int,
        contentDescription: Boolean = false
    ): SemanticsNodeInteraction {
        val text = composeTestRule.activity.getString(id)
        return if (contentDescription) {
            composeTestRule.onNodeWithContentDescription(text)
        } else {
            // Buttons in the starter code render their labels in upper case
            composeTestRule.onNodeWithText(text, ignoreCase = true)
        }
    }

    private fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
    }
}
