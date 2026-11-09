package com.example.knrtu_mobile_systems.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.knrtu_mobile_systems.R
import com.example.knrtu_mobile_systems.model.Category
import com.example.knrtu_mobile_systems.ui.utils.MyCityContentType
import com.example.knrtu_mobile_systems.ui.utils.MyCityNavigationType

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
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val navigationType: MyCityNavigationType
    val contentType: MyCityContentType
    when (windowSize) {
        WindowWidthSizeClass.Medium -> {
            navigationType = MyCityNavigationType.NAVIGATION_RAIL
            contentType = MyCityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = MyCityContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = MyCityNavigationType.CATEGORIES_SCREEN
            contentType = MyCityContentType.LIST_ONLY
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )
    // On larger screens categories live in the rail / drawer, so the Categories route shows places
    val showCategoriesScreen = navigationType == MyCityNavigationType.CATEGORIES_SCREEN
    val isShowingDetails = currentScreen == MyCityScreen.Details &&
        contentType == MyCityContentType.LIST_ONLY

    val title = when {
        isShowingDetails -> stringResource(uiState.currentPlace.name)
        currentScreen == MyCityScreen.Categories && showCategoriesScreen ->
            stringResource(R.string.app_name)
        else -> stringResource(uiState.currentCategory.title)
    }
    val canNavigateBack = when (navigationType) {
        MyCityNavigationType.CATEGORIES_SCREEN -> navController.previousBackStackEntry != null
        MyCityNavigationType.NAVIGATION_RAIL -> isShowingDetails
        MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER -> false
    }

    if (contentType == MyCityContentType.LIST_AND_DETAIL) {
        // List and details are visible together: Back leaves the app
        val activity = LocalContext.current as? Activity
        BackHandler { activity?.finish() }
    }

    val onRailCategoryClick: (Category) -> Unit = {
        viewModel.selectCategory(it)
        navController.popBackStack(MyCityScreen.Categories.name, inclusive = false)
    }

    val content = @Composable {
        Scaffold(
            topBar = {
                MyCityAppBar(
                    title = title,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navController.navigateUp() },
                )
            }
        ) { innerPadding ->
            Row(modifier = Modifier.fillMaxSize()) {
                if (navigationType == MyCityNavigationType.NAVIGATION_RAIL) {
                    MyCityNavigationRail(
                        categories = uiState.categories,
                        currentCategory = uiState.currentCategory,
                        onCategoryClick = onRailCategoryClick,
                        modifier = Modifier
                            .padding(top = innerPadding.calculateTopPadding())
                            .testTag(stringResource(R.string.navigation_rail))
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = MyCityScreen.Categories.name,
                    modifier = Modifier.weight(1f),
                ) {
                    val placesContent = @Composable {
                        if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                            PlacesAndDetailsScreen(
                                places = uiState.places,
                                selectedPlace = uiState.currentPlace,
                                onPlaceClick = { viewModel.selectPlace(it) },
                                contentPadding = innerPadding,
                            )
                        } else {
                            PlacesScreen(
                                places = uiState.places,
                                onPlaceClick = {
                                    viewModel.selectPlace(it)
                                    navController.navigate(MyCityScreen.Details.name)
                                },
                                contentPadding = innerPadding,
                            )
                        }
                    }
                    composable(route = MyCityScreen.Categories.name) {
                        if (showCategoriesScreen) {
                            CategoriesScreen(
                                categories = uiState.categories,
                                onCategoryClick = {
                                    viewModel.selectCategory(it)
                                    navController.navigate(MyCityScreen.Places.name)
                                },
                                contentPadding = innerPadding,
                            )
                        } else {
                            placesContent()
                        }
                    }
                    composable(route = MyCityScreen.Places.name) {
                        placesContent()
                    }
                    composable(route = MyCityScreen.Details.name) {
                        if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                            placesContent()
                        } else {
                            PlaceDetailsScreen(
                                place = uiState.currentPlace,
                                contentPadding = innerPadding,
                            )
                        }
                    }
                }
            }
        }
    }

    if (navigationType == MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    modifier = Modifier.width(dimensionResource(R.dimen.drawer_width))
                ) {
                    MyCityDrawerContent(
                        categories = uiState.categories,
                        currentCategory = uiState.currentCategory,
                        onCategoryClick = onRailCategoryClick,
                    )
                }
            },
            modifier = modifier.testTag(stringResource(R.string.navigation_drawer))
        ) {
            content()
        }
    } else {
        Row(modifier = modifier) {
            content()
        }
    }
}

@Composable
private fun MyCityNavigationRail(
    categories: List<Category>,
    currentCategory: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        categories.forEach { category ->
            NavigationRailItem(
                selected = category == currentCategory,
                onClick = { onCategoryClick(category) },
                icon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = stringResource(category.title)
                    )
                }
            )
        }
    }
}

@Composable
private fun MyCityDrawerContent(
    categories: List<Category>,
    currentCategory: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
        categories.forEach { category ->
            NavigationDrawerItem(
                selected = category == currentCategory,
                onClick = { onCategoryClick(category) },
                label = { Text(stringResource(category.title)) },
                icon = { Icon(imageVector = category.icon, contentDescription = null) },
            )
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
