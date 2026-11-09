package com.example.knrtu_mobile_systems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import com.example.knrtu_mobile_systems.model.Category
import com.example.knrtu_mobile_systems.ui.CategoriesScreen
import com.example.knrtu_mobile_systems.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme {
                Surface {
                    CategoriesScreen(
                        categories = Category.entries,
                        onCategoryClick = {},
                        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
                    )
                }
            }
        }
    }
}
