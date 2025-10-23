package com.gabrielfreire.sorteiopro

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gabrielfreire.sorteiopro.ui.navigation.AppNavigation
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme
import com.gabrielfreire.sorteiopro.utils.TestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppFlowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun AppFlow_shouldNavigateFromPrincipalToResultadoAndBack() {
        composeTestRule.setContent {
            SorteioProTheme {
                AppNavigation()
            }
        }

        composeTestRule.onNodeWithTag(testTag = TestTags.CAMPO_TAMANHO)
            .assertIsEnabled().assertIsDisplayed()

        composeTestRule.onNodeWithTag(testTag = TestTags.CAMPO_CABECAS)
            .assertIsEnabled().assertIsDisplayed().performTextInput(text = "Alice; Bruno")

        composeTestRule.onNodeWithTag(testTag = TestTags.CAMPO_CABECAS)
            .performImeAction()

        composeTestRule.onNodeWithTag(testTag = TestTags.CAMPO_DEMAIS)
            .assertIsEnabled().assertIsDisplayed().performTextInput(text = "1; 2; 3; 4; 5; 6")

        composeTestRule.onNodeWithTag(testTag = TestTags.CAMPO_DEMAIS)
            .performImeAction()

        composeTestRule.onNodeWithTag(testTag = TestTags.BOTAO_SORTEAR)
            .assertIsEnabled().assertIsDisplayed().performClick()

        composeTestRule.onNodeWithTag(testTag = TestTags.TELA_RESULTADO)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(testTag = TestTags.MENSAGEM_RESULTADO)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(testTag = TestTags.BOTAO_VOLTAR)
            .assertIsEnabled().assertIsDisplayed().performClick()
    }
}