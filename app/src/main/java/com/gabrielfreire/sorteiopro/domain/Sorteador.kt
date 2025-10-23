package com.gabrielfreire.sorteiopro.domain

import com.gabrielfreire.sorteiopro.data.ResultadoSorteio

class Sorteador {

    fun sortearGruposComLideres(
        textoCabecasDeChave: String,
        textoDemaisNomes: String,
        tamanhoDoGrupo: Int
    ): ResultadoSorteio {

        if (tamanhoDoGrupo <= 1) {
            return ResultadoSorteio(grupos = emptyList(), sobrantes = emptyList())
        }

        val cabecasDeChave = textoCabecasDeChave.split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .shuffled()

        var demaisNomes = textoDemaisNomes.split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .shuffled()

        val numCabecas = cabecasDeChave.size
        val gruposFinais = mutableListOf<List<String>>()
        val sobrantes = mutableListOf<String>()
        val faltam = tamanhoDoGrupo - 1

        if (numCabecas == 0 || demaisNomes.size < faltam) {
            sobrantes.addAll(elements = cabecasDeChave)
            sobrantes.addAll(elements = demaisNomes)

            return ResultadoSorteio(grupos = emptyList(), sobrantes)
        }

        for (i in 0 until numCabecas) {
            val grupoEmConstrucao = mutableListOf<String>()
            val cabeca = cabecasDeChave[i]

            grupoEmConstrucao.add(cabeca)

            if (demaisNomes.size >= faltam) {
                val completadores = demaisNomes.take(n = faltam)

                demaisNomes = demaisNomes.drop(n = faltam)

                grupoEmConstrucao.addAll(elements = completadores)

                gruposFinais.add(grupoEmConstrucao)
            } else {
                sobrantes.add(cabeca)

                val cabecasRestantes = cabecasDeChave.subList(i + 1, cabecasDeChave.size)

                sobrantes.addAll(elements = cabecasRestantes)

                break
            }
        }

        sobrantes.addAll(elements = demaisNomes)

        return ResultadoSorteio(grupos = gruposFinais, sobrantes = sobrantes)
    }
}