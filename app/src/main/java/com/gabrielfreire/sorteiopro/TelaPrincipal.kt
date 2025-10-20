package com.gabrielfreire.sorteiopro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal(onSortearClicked: (lideres: String, comuns: String, tamanho: Int) -> Unit) {
    var tamanhoDoGrupoTexto by remember { mutableStateOf(value = "4") }
    var nomesLideres by remember { mutableStateOf(value = "") }
    var nomesComuns by remember { mutableStateOf(value = "") }

    val tamanhoDoGrupo: Int = tamanhoDoGrupoTexto.toIntOrNull() ?: 0
    val lideresCount = nomesLideres.split(";").filter { it.trim().isNotEmpty() }.size
    val comunsCount = nomesComuns.split(";").filter { it.trim().isNotEmpty() }.size
    val isEnabled = tamanhoDoGrupo >= 2 && lideresCount >= 1 && comunsCount >= (tamanhoDoGrupo - 1)

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.tela_principal_titulo))
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = tamanhoDoGrupoTexto,
                onValueChange = { novoTexto ->
                    if (novoTexto.length <= 2 && novoTexto.all { it.isDigit() }) {
                        tamanhoDoGrupoTexto = novoTexto
                    }
                },
                label = {
                    Text(text = stringResource(id = R.string.tela_principal_tamanho_label))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f)
            ) {
                OutlinedTextField(
                    value = nomesLideres,
                    onValueChange = { nomesLideres = it },
                    label = {
                        Text(text = stringResource(id = R.string.tela_principal_lideres_label))
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.tela_principal_lideres_placeholder))
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f)
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                OutlinedTextField(
                    value = nomesComuns,
                    onValueChange = { nomesComuns = it },
                    label = {
                        Text(text = stringResource(id = R.string.tela_principal_membros_label))
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.tela_principal_membros_placeholder))
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f)
                )
            }


            Spacer(modifier = Modifier.height(height = 24.dp))

            Button(
                onClick = {
                    onSortearClicked(
                        nomesLideres,
                        nomesComuns,
                        tamanhoDoGrupo
                    )
                },
                enabled = isEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        id = R.string.tela_principal_botao_sortear,
                        tamanhoDoGrupo
                    )
                )
            }

            if (isEnabled.not()) {
                val minMembros = if (tamanhoDoGrupo >= 2) tamanhoDoGrupo - 1 else 1

                Text(
                    text = stringResource(
                        id = R.string.tela_principal_minimo_sortear,
                        minMembros
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}