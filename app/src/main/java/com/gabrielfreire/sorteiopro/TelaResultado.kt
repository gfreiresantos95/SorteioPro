package com.gabrielfreire.sorteiopro

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.tela_resultado_voltar)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            val textoSobra = if (resultado.sobrantes.isEmpty()) {
                stringResource(id = R.string.sobra_sucesso, numNomesTotais)
            } else {
                stringResource(
                    id = R.string.sobra_aviso,
                    resultado.sobrantes.size,
                    resultado.sobrantes.joinToString(separator = ", ")
                )
            }

            val color = if (resultado.sobrantes.isEmpty()) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }

            Text(
                text = textoSobra,
                style = MaterialTheme.typography.titleMedium,
                color = color,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(all = 12.dp)
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            if (resultado.grupos.isEmpty()) {
                Text(text = stringResource(id = R.string.sobra_nenhum_grupo))
            } else {
                Text(
                    text = stringResource(id = R.string.tela_resultado_grupos_formados),
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                LazyColumn {
                    itemsIndexed(items = resultado.grupos) { index, grupo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                        ) {
                            Column(modifier = Modifier.padding(all = 16.dp)) {
                                Text(
                                    text = stringResource(
                                        id = R.string.tela_resultado_grupo_label,
                                        index + 1,
                                        grupo.size
                                    ),
                                    style = MaterialTheme.typography.titleMedium
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