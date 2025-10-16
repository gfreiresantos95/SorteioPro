package com.gabrielfreire.sorteiopro

class Sorteador {
    fun sortearGrupos(textoNomes: String): ResultadoSorteio {
        // 1. Processar e Limpar Nomes
        val nomesComTrim = textoNomes
            .split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        if (nomesComTrim.isEmpty()) {
            return ResultadoSorteio(emptyList(), emptyList())
        }

        // 2. Sortear (Embaralhar)
        val nomesEmbaralhados = nomesComTrim.shuffled().toMutableList()

        // Otimização: A função chunked do Kotlin faz exatamente isso!
        // Ela divide a lista em sublistas de tamanho N, e o restante fica na última sublista.
        // No nosso caso, pegamos apenas os grupos completos.

        val gruposCompletos = nomesEmbaralhados
            .chunked(TAMANHO_DO_GRUPO)
            .filter { it.size == TAMANHO_DO_GRUPO }

        // Os nomes que sobraram
        // Calculamos quantos nomes foram usados: número de grupos * tamanho do grupo
        val nomesUsados = gruposCompletos.sumOf { it.size }
        val nomesSobrantes = nomesEmbaralhados.drop(nomesUsados) // Pula os nomes usados

        return ResultadoSorteio(gruposCompletos, nomesSobrantes)
    }

    companion object {
        const val TAMANHO_DO_GRUPO = 4
    }
}