package com.gabrielfreire.sorteiopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var resultadoSorteio by remember { mutableStateOf<ResultadoSorteio?>(null) }

    NavHost(
        navController = navController,
        startDestination = Screen.Principal.route
    ) {
        composable(route = Screen.Principal.route) {
            TelaPrincipal(
                onSortearClicked = { textoLideres, textoComuns ->
                    resultadoSorteio = Sorteador().sortearGruposComLideres(
                        textoCabecasDeChave = textoLideres,
                        textoDemaisNomes = textoComuns
                    )

                    navController.navigate(route = Screen.Resultado.route)
                }
            )
        }

        composable(route = Screen.Resultado.route) {
            resultadoSorteio?.let { resultado ->
                TelaResultado(
                    resultado = resultado,
                    onBackClicked = { navController.popBackStack() }
                )
            } ?: run {
                LaunchedEffect(key1 = Unit) { navController.popBackStack() }
            }
        }
    }
}