package com.gabrielfreire.sorteiopro

data class ResultadoSorteio(
    val grupos: List<List<String>>,
    val sobrantes: List<String>
)