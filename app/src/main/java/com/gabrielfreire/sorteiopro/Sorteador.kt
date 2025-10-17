package com.gabrielfreire.sorteiopro

class Sorteador {
    // A função agora recebe a lista de objetos já processada.
    fun sortearGruposAprimorado(listaNomesComFlags: List<NomeComFlag>): ResultadoSorteio {

        // 1. Separar Cabeças de Chave e Demais Nomes
        val cabecasDeChave = listaNomesComFlags.filter { it.isCabecaDeChave }
            .map { it.nome }
            .shuffled() // Embaralha para que a ordem dos líderes nos grupos seja aleatória

        var demaisNomes = listaNomesComFlags.filter { it.isCabecaDeChave.not() }
            .map { it.nome }
            .shuffled() // Embaralha o restante dos nomes

        // 2. Determinar o número máximo de grupos
        val numCabecas = cabecasDeChave.size
        val numGruposPossiveis = numCabecas // Cada Cabeça de Chave forma um grupo

        // Lista para armazenar os grupos finais
        val gruposFinais = mutableListOf<List<String>>()

        // Lista para armazenar nomes que não foram alocados
        val sobrantes = mutableListOf<String>()

        // 3. Alocar Cabeças de Chave e Completar Grupos
        for (i in 0 until numGruposPossiveis) {
            val grupoEmConstrucao = mutableListOf<String>()
            val cabeca = cabecasDeChave[i]

            // Adiciona o Cabeça de Chave
            grupoEmConstrucao.add(cabeca)

            // Calcula quantos membros faltam no grupo (TAMANHO_DO_GRUPO - 1)
            val faltam = TAMANHO_DO_GRUPO - 1

            // 4. Preencher o restante do grupo com nomes aleatórios
            if (demaisNomes.size >= faltam) {
                // Pega os 'faltam' nomes e os remove da lista 'demaisNomes'
                val completadores = demaisNomes.take(faltam)
                demaisNomes = demaisNomes.drop(faltam) // Atualiza a lista removendo os usados

                grupoEmConstrucao.addAll(completadores)
                gruposFinais.add(grupoEmConstrucao)

            } else {
                // Se não houver nomes suficientes para completar o grupo, o Cabeça de Chave sobrou
                // (e qualquer nome restante no demaisNomes também sobrará)
                sobrantes.add(cabeca)
                break // Interrompe o loop, pois os Cabeças de Chave restantes também sobrarão
            }
        }

        // 5. Nomes Sobrantes
        // Qualquer Cabeça de Chave não utilizado (se o loop quebrou)
        if (sobrantes.isNotEmpty()) {
            val cabecasSobrando = cabecasDeChave.subList(gruposFinais.size, cabecasDeChave.size)
            sobrantes.addAll(cabecasSobrando)
        }

        // Todos os nomes restantes são considerados sobrantes
        sobrantes.addAll(demaisNomes)

        return ResultadoSorteio(gruposFinais, sobrantes)
    }

    companion object {
        const val TAMANHO_DO_GRUPO = 4
    }
}