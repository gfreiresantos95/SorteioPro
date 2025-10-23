package com.gabrielfreire.sorteiopro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfreire.sorteiopro.utils.DadosDeMock
import com.gabrielfreire.sorteiopro.R
import com.gabrielfreire.sorteiopro.data.ResultadoSorteio
import com.gabrielfreire.sorteiopro.ui.theme.SorteioProTheme
import com.gabrielfreire.sorteiopro.utils.TestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaResultado(
    resultado: ResultadoSorteio,
    tamanhoGrupoEsperado: Int,
    onBackClicked: () -> Unit
) {

    val numNomesTotais = resultado.grupos.size * tamanhoGrupoEsperado

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.tela_resultado_titulo)) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked,
                        modifier = Modifier.testTag(tag = TestTags.BOTAO_VOLTAR)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.tela_resultado_voltar)
                        )
                    }
                },
                modifier = Modifier.testTag(tag = TestTags.TELA_RESULTADO)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp)
        ) {
            val textoSobra = if (resultado.sobrantes.isEmpty()) {
                stringResource(
                    id = R.string.sobra_sucesso,
                    numNomesTotais
                )
            } else {
                stringResource(
                    id = R.string.sobra_aviso,
                    resultado.sobrantes.size,
                    resultado.sobrantes.joinToString(separator = ", ")
                )
            }

            Text(
                text = textoSobra,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(all = 12.dp)
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            val textoResultado = if (resultado.grupos.isEmpty()) {
                stringResource(id = R.string.sobra_nenhum_grupo)
            } else {
                stringResource(id = R.string.tela_resultado_grupos_formados)
            }

            Text(
                text = textoResultado,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.testTag(tag = TestTags.MENSAGEM_RESULTADO)
            )

            if (resultado.grupos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(height = 8.dp))

                LazyColumn(modifier = Modifier.testTag(tag = TestTags.LISTA_RESULTADO)) {
                    itemsIndexed(items = resultado.grupos) { index, grupo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(all = 16.dp)) {
                                Text(
                                    text = stringResource(
                                        id = R.string.tela_resultado_grupo_label,
                                        index + 1,
                                        grupo.size
                                    ),
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.testTag(tag = TestTags.TITULO_GRUPO)
                                )

                                Spacer(modifier = Modifier.height(height = 4.dp))

                                Text(
                                    text = grupo.joinToString(separator = "\n"),
                                    style = MaterialTheme.typography.bodyLarge,
                                    lineHeight = 24.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Claro - Resultado Completo")
@Composable
fun TelaResultadoCompletoPreviewLight() {
    SorteioProTheme(darkTheme = false) {
        TelaResultado(
            resultado = DadosDeMock.resultadoCompleto,
            tamanhoGrupoEsperado = DadosDeMock.TAMANHO_GRUPO,
            onBackClicked = {}
        )
    }
}

@Preview(showBackground = true, name = "Escuro - Resultado Completo")
@Composable
fun TelaResultadoCompletoPreviewDark() {
    SorteioProTheme(darkTheme = true) {
        TelaResultado(
            resultado = DadosDeMock.resultadoCompleto,
            tamanhoGrupoEsperado = DadosDeMock.TAMANHO_GRUPO,
            onBackClicked = {}
        )
    }
}

@Preview(showBackground = true, name = "Claro - Resultado Com Sobra")
@Composable
fun TelaResultadoComSobraPreviewLight() {
    SorteioProTheme(darkTheme = false) {
        TelaResultado(
            resultado = DadosDeMock.resultadoComSobras,
            tamanhoGrupoEsperado = DadosDeMock.TAMANHO_GRUPO,
            onBackClicked = {}
        )
    }
}

@Preview(showBackground = true, name = "Escuro - Resultado Com Sobra")
@Composable
fun TelaResultadoComSobraPreviewDark() {
    SorteioProTheme(darkTheme = true) {
        TelaResultado(
            resultado = DadosDeMock.resultadoComSobras,
            tamanhoGrupoEsperado = DadosDeMock.TAMANHO_GRUPO,
            onBackClicked = {}
        )
    }
}