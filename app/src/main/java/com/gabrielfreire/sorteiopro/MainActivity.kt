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
    // O resultado do sorteio deve ser compartilhado entre telas.
    // Usaremos um ViewModel, mas para simplicidade, um estado aqui serve.
    var resultadoSorteio by remember { mutableStateOf<ResultadoSorteio?>(null) }

    NavHost(
        navController = navController,
        startDestination = Screen.Principal.route
    ) {
        // Primeira Tela: Entrada de Nomes
        composable(Screen.Principal.route) {
            TelaPrincipal(
                onSortearClicked = { textoNomes ->
                    val listaProcessada = preProcessarNomes(textoNomes)
                    resultadoSorteio = Sorteador().sortearGruposAprimorado(listaProcessada)
                    navController.navigate(Screen.Resultado.route)
                }
            )
        }

        // Segunda Tela: Resultado do Sorteio
        composable(Screen.Resultado.route) {
            // Verifica se há resultado para exibir
            resultadoSorteio?.let { resultado ->
                TelaResultado(
                    resultado = resultado,
                    onBackClicked = {
                        // Navega de volta para a tela principal
                        navController.popBackStack()
                    }
                )
            } ?: run {
                // Caso algo dê errado, volta para a tela principal.
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }
            }
        }
    }
}

fun preProcessarNomes(textoNomes: String): List<NomeComFlag> {
    return textoNomes
        .split(";")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map { nomeCompleto ->
            val isCabecaDeChave = nomeCompleto.startsWith("*")
            // Remove o asterisco do nome final
            val nomeLimpo =
                if (isCabecaDeChave) nomeCompleto.drop(1).trim() else nomeCompleto.trim()
            NomeComFlag(nomeLimpo, isCabecaDeChave)
        }
}