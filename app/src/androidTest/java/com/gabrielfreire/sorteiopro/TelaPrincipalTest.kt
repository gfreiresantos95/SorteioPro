package com.gabrielfreire.sorteiopro

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gabrielfreire.sorteiopro.ui.screens.TelaPrincipal
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme
import com.gabrielfreire.sorteiopro.utils.TestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TelaPrincipalTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun preencherCampo(testTag: String, texto: String) {
        composeTestRule.onNodeWithTag(testTag = testTag)
            .performTextInput(text = texto)

        composeTestRule.onNodeWithTag(testTag = testTag)
            .performImeAction()
    }

    @Test
    fun GIVEN_campos_vazios_THEN_botao_Sortear_deve_estar_desabilitado() {
        composeTestRule.setContent {
            SorteioProTheme {
                TelaPrincipal(onSortearClicked = { _, _, _ -> })
            }
        }

        composeTestRule.onNodeWithTag(testTag = TestTags.BOTAO_SORTEAR)
            .assertIsDisplayed().assertIsNotEnabled()
    }

    @Test
    fun GIVEN_CabecasDeChave_e_Nomes_faltantes_THEN_botao_Sortear_deve_estar_desabilitado() {
        composeTestRule.setContent {
            SorteioProTheme {
                TelaPrincipal(onSortearClicked = { _, _, _ -> })
            }
        }

        preencherCampo(testTag = TestTags.CAMPO_CABECAS, texto = "L1")

        composeTestRule.onNodeWithTag(testTag = TestTags.BOTAO_SORTEAR)
            .assertIsDisplayed().assertIsNotEnabled()
    }

    @Test
    fun GIVEN_dados_completos_THEN_botao_Sortear_deve_estar_habilitado() {
        composeTestRule.setContent {
            SorteioProTheme {
                TelaPrincipal(onSortearClicked = { _, _, _ -> })
            }
        }

        preencherCampo(testTag = TestTags.CAMPO_CABECAS, texto = "L1; L2")
        preencherCampo(testTag = TestTags.CAMPO_DEMAIS, texto = "M1; M2; M3; M4")

        composeTestRule.onNodeWithTag(testTag = TestTags.BOTAO_SORTEAR)
            .assertIsDisplayed().assertIsEnabled()
    }
}