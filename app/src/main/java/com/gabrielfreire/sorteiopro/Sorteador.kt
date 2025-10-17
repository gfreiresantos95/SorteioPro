package com.gabrielfreire.sorteiopro

class Sorteador {
    fun sortearGruposComLideres(
        textoCabecasDeChave: String,
        textoDemaisNomes: String
    ): ResultadoSorteio {
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

        if (numCabecas == 0) {
            sobrantes.addAll(elements = demaisNomes)
            return ResultadoSorteio(grupos = emptyList(), sobrantes = sobrantes)
        }

        for (i in 0 until numCabecas) {
            val grupoEmConstrucao = mutableListOf<String>()
            val cabeca = cabecasDeChave[i]

            grupoEmConstrucao.add(cabeca)

            val faltam = TAMANHO_DO_GRUPO - 1

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

    companion object {
        const val TAMANHO_DO_GRUPO = 4
    }
}