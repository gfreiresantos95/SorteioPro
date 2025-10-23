package com.gabrielfreire.sorteiopro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabrielfreire.sorteiopro.data.ResultadoSorteio
import com.gabrielfreire.sorteiopro.data.Screen
import com.gabrielfreire.sorteiopro.domain.Sorteador
import com.gabrielfreire.sorteiopro.ui.screens.TelaPrincipal
import com.gabrielfreire.sorteiopro.ui.screens.TelaResultado

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    var resultadoSorteio by remember { mutableStateOf<ResultadoSorteio?>(value = null) }
    var tamanhoGrupoSelecionado by remember { mutableIntStateOf(value = 0) }

    NavHost(
        navController = navController,
        startDestination = Screen.Principal.route
    ) {
        composable(route = Screen.Principal.route) {
            TelaPrincipal(
                onSortearClicked = { textoLideres, textoComuns, tamanho ->
                    tamanhoGrupoSelecionado = tamanho

                    resultadoSorteio =
                        Sorteador().sortearGruposComLideres(
                            textoCabecasDeChave = textoLideres,
                            textoDemaisNomes = textoComuns,
                            tamanhoDoGrupo = tamanho
                        )

                    navController.navigate(route = Screen.Resultado.route)
                }
            )
        }

        composable(route = Screen.Resultado.route) {
            resultadoSorteio?.let { resultado ->
                TelaResultado(
                    resultado = resultado,
                    tamanhoGrupoEsperado = tamanhoGrupoSelecionado,
                    onBackClicked = { navController.popBackStack() }
                )
            } ?: run {
                LaunchedEffect(key1 = Unit) { navController.popBackStack() }
            }
        }
    }
}