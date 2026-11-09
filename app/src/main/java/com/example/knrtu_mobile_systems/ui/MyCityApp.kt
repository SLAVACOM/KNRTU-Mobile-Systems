package com.example.knrtu_mobile_systems.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.knrtu_mobile_systems.R

/**
 * Routes of the app.
 */
enum class MyCityScreen {
    Categories,
    Places,
    Details,
}

@Composable
fun MyCityApp(
    modifier: Modifier = Modifier,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )

    val title = when (currentScreen) {
        MyCityScreen.Categories -> stringResource(R.string.app_name)
        MyCityScreen.Places -> stringResource(uiState.currentCategory.title)
        MyCityScreen.Details -> stringResource(uiState.currentPlace.name)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MyCityAppBar(
                title = title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.name,
            modifier = Modifier.fillMaxSize(),
        ) {
            composable(route = MyCityScreen.Categories.name) {
                CategoriesScreen(
                    categories = uiState.categories,
                    onCategoryClick = {
                        viewModel.selectCategory(it)
                        navController.navigate(MyCityScreen.Places.name)
                    },
                    contentPadding = innerPadding,
                )
            }
            composable(route = MyCityScreen.Places.name) {
                PlacesScreen(
                    places = uiState.places,
                    onPlaceClick = {
                        viewModel.selectPlace(it)
                        navController.navigate(MyCityScreen.Details.name)
                    },
                    contentPadding = innerPadding,
                )
            }
            composable(route = MyCityScreen.Details.name) {
                PlaceDetailsScreen(
                    place = uiState.currentPlace,
                    contentPadding = innerPadding,
                )
            }
        }
    }
}

/**
 * Top app bar with the screen title and the Up button when back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
