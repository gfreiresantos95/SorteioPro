package com.gabrielfreire.sorteiopro

sealed class Screen(val route: String) {
    data object Principal : Screen(route = "principal")
    data object Resultado : Screen(route = "resultado")
}