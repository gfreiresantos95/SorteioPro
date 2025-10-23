package com.gabrielfreire.sorteiopro

import com.gabrielfreire.sorteiopro.domain.Sorteador
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SorteadorTest {

    private lateinit var sorteador: Sorteador

    @Before
    fun setup() {
        sorteador = Sorteador()
    }

    @Test
    fun `GIVEN tamanhoDoGrupo menor ou igual a 1 THEN deve retornar grupos e sobrantes vazios`() {
        val resultado1 = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "A;B",
            textoDemaisNomes = "1;2",
            tamanhoDoGrupo = 1
        )

        val resultado2 = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "A;B",
            textoDemaisNomes = "1;2",
            tamanhoDoGrupo = 0
        )

        assertTrue(resultado1.grupos.isEmpty())
        assertTrue(resultado1.sobrantes.isEmpty())

        assertTrue(resultado2.grupos.isEmpty())
        assertTrue(resultado2.sobrantes.isEmpty())
    }

    @Test
    fun `GIVEN sem CabecasDeChave THEN deve retornar todos os DemaisNomes como sobrantes`() {
        val tamanhoGrupo = 3

        val resultado = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "",
            textoDemaisNomes = "1;2;3;4;5",
            tamanhoDoGrupo = tamanhoGrupo
        )

        assertTrue(resultado.grupos.isEmpty())
        assertEquals(5, resultado.sobrantes.size)
    }

    @Test
    fun `GIVEN CabecasDeChave mas sem DemaisNomes suficientes THEN deve retornar todos como sobrantes`() {
        val tamanhoGrupo = 3

        val resultado = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "A;B",
            textoDemaisNomes = "1",
            tamanhoDoGrupo = tamanhoGrupo
        )

        assertEquals(tamanhoGrupo, resultado.sobrantes.size)
        assertTrue(resultado.grupos.isEmpty())
        assertTrue(resultado.sobrantes.contains("A"))
        assertTrue(resultado.sobrantes.contains("1"))
    }

    @Test
    fun `GIVEN dados suficientes WHEN sortear grupos de 3 THEN deve formar 2 grupos completos sem sobrantes`() {
        val tamanhoGrupo = 3

        val resultado = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "A;B",
            textoDemaisNomes = "1;2;3;4",
            tamanhoDoGrupo = tamanhoGrupo
        )

        assertEquals(2, resultado.grupos.size)
        assertTrue(resultado.sobrantes.isEmpty())

        resultado.grupos.forEach { grupo ->
            assertEquals(tamanhoGrupo, grupo.size)
        }

        val todosOsMembros = resultado.grupos.flatten()

        assertTrue(todosOsMembros.contains("A"))
        assertTrue(todosOsMembros.contains("B"))
    }

    @Test
    fun `GIVEN cabecas de chave extra WHEN sortear grupos de 4 THEN deve retornar cabecas de chave e nomes como sobrantes`() {
        val resultado = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "A;B;C;D",
            textoDemaisNomes = "1;2;3;4;5;6;7;8",
            tamanhoDoGrupo = 4
        )

        assertEquals(2, resultado.grupos.size)

        resultado.grupos.forEach { grupo ->
            assertEquals(4, grupo.size)
        }

        assertEquals(4, resultado.sobrantes.size)
    }

    @Test
    fun `GIVEN nomes extra WHEN sortear grupos de 2 THEN deve retornar nomes extras como sobrantes`() {
        val resultado = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "L1;L2",
            textoDemaisNomes = "M1;M2;M3;M4",
            tamanhoDoGrupo = 2
        )

        assertEquals(2, resultado.grupos.size)
        assertEquals(2, resultado.sobrantes.size)

        resultado.grupos.forEach { grupo ->
            assertEquals(2, grupo.size)
        }
    }

    @Test
    fun `GIVEN listas de entrada com espacos em branco e vazios THEN deve ignorar e processar corretamente`() {
        val resultado = sorteador.sortearGruposComLideres(
            textoCabecasDeChave = "  L1 ; L2; ;",
            textoDemaisNomes = " M1;M2 ; M3 ;",
            tamanhoDoGrupo = 3
        )

        assertEquals(1, resultado.grupos.size)
        assertEquals(2, resultado.sobrantes.size)
        assertEquals(3, resultado.grupos.first().size)
    }
}