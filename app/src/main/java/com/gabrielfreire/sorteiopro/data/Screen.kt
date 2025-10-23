package com.gabrielfreire.sorteiopro.data

sealed class Screen(val route: String) {
    data object Principal : Screen(route = "principal")
    data object Resultado : Screen(route = "resultado")
}