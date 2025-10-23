package com.gabrielfreire.sorteiopro.utils

import com.gabrielfreire.sorteiopro.data.ResultadoSorteio

object DadosDeMock {

    private val grupos = listOf(
        listOf("Alice (Líder)", "Bruno", "Cecília", "Davi"),
        listOf("Eduardo (Líder)", "Fabiola", "Guilherme", "Helena")
    )

    private val sobrantes = listOf("Marco", "Nathalia", "Olivia")

    const val TAMANHO_GRUPO = 4


    val resultadoCompleto = ResultadoSorteio(grupos = grupos, sobrantes = emptyList())
    val resultadoComSobras = ResultadoSorteio(grupos = grupos, sobrantes = sobrantes)
    val resultadoSemGrupos = ResultadoSorteio(grupos = emptyList(), sobrantes = emptyList())
}