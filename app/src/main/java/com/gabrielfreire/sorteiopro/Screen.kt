package com.gabrielfreire.sorteiopro

sealed class Screen(val route: String) {
    data object Principal : Screen("principal")
    data object Resultado : Screen("resultado")
}