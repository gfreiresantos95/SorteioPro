package com.gabrielfreire.sorteiopro.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gabrielfreire.sorteiopro.ui.navigation.AppNavigation
import com.gabrielfreire.sorteiopro.viewmodels.MainViewModel
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { mainViewModel.keepSplashOn.value }

        super.onCreate(savedInstanceState)

        setContent {
            SorteioProTheme {
                val isLoading by mainViewModel.isLoading.collectAsState()

                if (isLoading.not()) {
                    AppNavigation()
                }
            }
        }
    }
}