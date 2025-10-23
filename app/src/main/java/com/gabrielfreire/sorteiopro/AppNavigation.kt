package com.gabrielfreire.sorteiopro

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