package com.gabrielfreire.sorteiopro.data

data class ResultadoSorteio(
    val grupos: List<List<String>>,
    val sobrantes: List<String>
)