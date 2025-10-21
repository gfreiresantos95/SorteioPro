package com.gabrielfreire.sorteiopro

object DadosDeMock {

    fun resultadoCompleto(): ResultadoSorteio {
        val grupos = listOf(
            listOf("Alice (Líder)", "Bruno", "Cecília", "Davi"),
            listOf("Eduardo (Líder)", "Fabiola", "Guilherme", "Helena")
        )

        return ResultadoSorteio(grupos, sobrantes = emptyList())
    }

    fun resultadoComSobras(): ResultadoSorteio {
        val grupos = listOf(listOf("Igor (Líder)", "Juliana", "Kevin", "Larissa"))
        val sobrantes = listOf("Marco", "Nathalia", "Olivia")

        return ResultadoSorteio(grupos, sobrantes)
    }
}