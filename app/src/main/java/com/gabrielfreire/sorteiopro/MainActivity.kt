package com.gabrielfreire.sorteiopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SorteioProTheme {
                AppNavigation()
            }
        }
    }
}