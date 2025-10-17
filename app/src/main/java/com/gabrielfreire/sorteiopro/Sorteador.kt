package com.gabrielfreire.sorteiopro

class Sorteador {
    fun sortearGruposComLideres(
        textoCabecasDeChave: String,
        textoDemaisNomes: String
    ): ResultadoSorteio {
        // 1. Processar e Limpar Nomes
        val cabecasDeChave = textoCabecasDeChave.split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .shuffled() // Líderes já embaralhados

        var demaisNomes = textoDemaisNomes.split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .shuffled() // Demais nomes já embaralhados

        // 2. Definir Estrutura
        val numCabecas = cabecasDeChave.size
        val gruposFinais = mutableListOf<List<String>>()
        val sobrantes = mutableListOf<String>()

        // Se não houver líderes, não podemos formar grupos de forma controlada.
        if (numCabecas == 0) {
            // Trata a lista inteira de "demais nomes" como uma lista única a ser sorteada
            // Neste cenário, o código anterior (chunked) seria o ideal, mas vamos priorizar
            // a lógica de líderes. Se não há líderes, todos são sobrantes para fins de demonstração
            // de líderes, ou você pode querer rodar a lógica original de chunked aqui.
            // Por simplicidade, vamos considerar todos os 'demaisNomes' como sobrantes se não houver líderes.
            sobrantes.addAll(elements = demaisNomes)
            return ResultadoSorteio(grupos = emptyList(), sobrantes = sobrantes)
        }

        // 3. Alocar Cabeças de Chave e Completar Grupos

        // O número de grupos que tentaremos formar é igual ao número de líderes
        for (i in 0 until numCabecas) {
            val grupoEmConstrucao = mutableListOf<String>()
            val cabeca = cabecasDeChave[i]

            grupoEmConstrucao.add(cabeca) // Líder na primeira posição

            val faltam = TAMANHO_DO_GRUPO - 1

            // 4. Preencher o restante do grupo com nomes aleatórios
            if (demaisNomes.size >= faltam) {
                // Pega os 'faltam' nomes e os remove da lista 'demaisNomes'
                val completadores = demaisNomes.take(n = faltam)
                demaisNomes = demaisNomes.drop(n = faltam)

                grupoEmConstrucao.addAll(elements = completadores)
                gruposFinais.add(grupoEmConstrucao)
            } else {
                // Se não há nomes suficientes para completar o grupo:
                // O líder atual e todos os líderes restantes tornam-se sobrantes.
                sobrantes.add(cabeca)
                val cabecasRestantes = cabecasDeChave.subList(i + 1, cabecasDeChave.size)
                sobrantes.addAll(elements = cabecasRestantes)
                break // Interrompe o loop
            }
        }

        // 5. Adicionar todos os nomes restantes (não-líderes) aos sobrantes
        sobrantes.addAll(demaisNomes)

        return ResultadoSorteio(grupos = gruposFinais, sobrantes = sobrantes)
    }

    companion object {
        const val TAMANHO_DO_GRUPO = 4
    }
}