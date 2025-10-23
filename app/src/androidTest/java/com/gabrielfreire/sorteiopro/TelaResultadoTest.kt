package com.gabrielfreire.sorteiopro

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gabrielfreire.sorteiopro.ui.screens.TelaResultado
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme
import com.gabrielfreire.sorteiopro.utils.DadosDeMock
import com.gabrielfreire.sorteiopro.utils.TestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TelaResultadoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun GIVEN_resultado_completo_THEN_tela_deve_exibir_grupos_e_nao_exibir_sobrantes() {
        composeTestRule.setContent {
            SorteioProTheme {
                TelaResultado(
                    resultado = DadosDeMock.resultadoCompleto,
                    tamanhoGrupoEsperado = DadosDeMock.TAMANHO_GRUPO,
                    onBackClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(testTag = TestTags.MENSAGEM_RESULTADO)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(testTag = TestTags.LISTA_RESULTADO)
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(testTag = TestTags.TITULO_GRUPO)
            .assertCountEquals(2)
    }

    @Test
    fun GIVEN_nenhum_grupo_THEN_tela_nao_deve_exibir_grupos() {
        composeTestRule.setContent {
            SorteioProTheme {
                TelaResultado(
                    resultado = DadosDeMock.resultadoSemGrupos,
                    tamanhoGrupoEsperado = DadosDeMock.TAMANHO_GRUPO,
                    onBackClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(testTag = TestTags.MENSAGEM_RESULTADO)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(testTag = TestTags.LISTA_RESULTADO)
            .assertIsNotDisplayed()
    }
}