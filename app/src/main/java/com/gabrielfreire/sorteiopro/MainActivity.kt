package com.gabrielfreire.sorteiopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private var keepSplashOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { keepSplashOn }

        super.onCreate(savedInstanceState)

        setContent {
            SorteioProTheme {
                var isLoading by remember { mutableStateOf(value = true) }

                LaunchedEffect(key1 = Unit) {
                    delay(timeMillis = 1000L)

                    isLoading = false
                    keepSplashOn = false
                }

                if (isLoading.not()) {
                    AppNavigation()
                }
            }
        }
    }
}