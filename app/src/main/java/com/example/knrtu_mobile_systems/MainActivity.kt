package com.example.knrtu_mobile_systems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.knrtu_mobile_systems.data.recipes
import com.example.knrtu_mobile_systems.model.Recipe
import com.example.knrtu_mobile_systems.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    RecipesApp()
                }
            }
        }
    }
}

/**
 * Top-level composable: an app bar plus a scrollable, expandable list of the 30 daily recipes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_title),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_spacing)),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(recipes) { recipe ->
                RecipeListItem(recipe = recipe)
            }
        }
    }
}

/**
 * Expandable list item for a single day's recipe: a thumbnail, day/name/category always
 * visible, and a bigger photo plus the recipe description revealed on tap.
 */
@Composable
fun RecipeListItem(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "expandArrowRotation"
    )

    // Flat card: no shadow, cards read against the background purely through color contrast.
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RecipeThumbnail(recipe = recipe)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                ) {
                    Text(
                        text = stringResource(R.string.day_label, recipe.day),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(recipe.nameResId),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Text(
                        text = stringResource(recipe.category.labelResId),
                        style = MaterialTheme.typography.bodySmall,
                        color = recipe.category.color
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Filled.ExpandMore,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = stringResource(R.string.expand_button_content_description),
                        modifier = Modifier.rotate(rotation)
                    )
                }
            }
            if (expanded) {
                RecipePhoto(
                    recipe = recipe,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
                )
                Text(
                    text = stringResource(recipe.descriptionResId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

/**
 * Round photo of the dish shown in the collapsed row, with a small category badge overlaid.
 */
@Composable
fun RecipeThumbnail(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(dimensionResource(R.dimen.image_size))) {
        Image(
            painter = painterResource(recipe.imageResId),
            contentDescription = stringResource(
                R.string.recipe_image_content_description,
                stringResource(recipe.nameResId)
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
        CategoryBadge(
            recipe = recipe,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

/**
 * Large photo of the dish shown when a recipe is expanded.
 */
@Composable
fun RecipePhoto(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(recipe.imageResId),
        contentDescription = stringResource(
            R.string.recipe_image_content_description,
            stringResource(recipe.nameResId)
        ),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.recipe_banner_height))
            .clip(MaterialTheme.shapes.medium)
    )
}

/**
 * Small colored circle with the category icon, used as a badge on top of a recipe photo.
 */
@Composable
fun CategoryBadge(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(20.dp)
            .background(color = recipe.category.color, shape = CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.surface, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = recipe.category.icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(12.dp)
        )
    }
}

/**
 * Preview of what the UI looks like in light theme in the design tab.
 */
@Preview
@Composable
fun RecipesAppPreview() {
    RecipesTheme(darkTheme = false) {
        RecipesApp()
    }
}
