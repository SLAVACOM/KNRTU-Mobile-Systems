package com.example.knrtu_mobile_systems.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.knrtu_mobile_systems.R
import com.example.knrtu_mobile_systems.data.LocalPlacesDataProvider
import com.example.knrtu_mobile_systems.model.Place
import com.example.knrtu_mobile_systems.ui.theme.MyCityTheme

/**
 * Details of the selected [place].
 */
@Composable
fun PlaceDetailsScreen(
    place: Place,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = modifier
            .testTag(stringResource(R.string.details_screen))
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.details_header_height))
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                        )
                    )
                )
        ) {
            Icon(
                imageVector = place.category.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(dimensionResource(R.dimen.details_icon_size))
            )
        }
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = stringResource(place.name),
                style = MaterialTheme.typography.headlineSmall,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            ) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = stringResource(place.location),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                )
            }
            Text(
                text = stringResource(place.description),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

/**
 * List of places and details of the selected one side by side. Used on expanded screens.
 */
@Composable
fun PlacesAndDetailsScreen(
    places: List<Place>,
    selectedPlace: Place,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Row(modifier = modifier) {
        PlacesScreen(
            places = places,
            selectedPlace = selectedPlace,
            onPlaceClick = onPlaceClick,
            contentPadding = contentPadding,
            modifier = Modifier.weight(2f)
        )
        PlaceDetailsScreen(
            place = selectedPlace,
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(3f)
                .padding(end = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailsScreenPreview() {
    MyCityTheme {
        PlaceDetailsScreen(place = LocalPlacesDataProvider.allPlaces.first())
    }
}
