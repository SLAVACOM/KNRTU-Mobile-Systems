package com.example.knrtu_mobile_systems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.knrtu_mobile_systems.ui.MyCityApp
import com.example.knrtu_mobile_systems.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(this)
                    MyCityApp(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityCompactPreview() {
    MyCityTheme {
        Surface {
            MyCityApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun MyCityMediumPreview() {
    MyCityTheme {
        Surface {
            MyCityApp(windowSize = WindowWidthSizeClass.Medium)
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun MyCityExpandedPreview() {
    MyCityTheme {
        Surface {
            MyCityApp(windowSize = WindowWidthSizeClass.Expanded)
        }
    }
}
