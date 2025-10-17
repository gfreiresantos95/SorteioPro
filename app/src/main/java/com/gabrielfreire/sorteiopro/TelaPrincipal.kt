package com.gabrielfreire.sorteiopro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.gabrielfreire.sorteiopro.Sorteador.Companion.TAMANHO_DO_GRUPO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal(onSortearClicked: (String, String) -> Unit) {
    var nomesLideres by remember { mutableStateOf(value = "") }
    var nomesComuns by remember { mutableStateOf(value = "") }

    val lideresValidos = nomesLideres.split(";").filter { it.trim().isNotEmpty() }.size
    val comunsValidos = nomesComuns.split(";").filter { it.trim().isNotEmpty() }.size
    val isEnabled = lideresValidos >= 1 && comunsValidos >= (TAMANHO_DO_GRUPO - 1)

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Sortear Grupos") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nomesLideres,
                onValueChange = { nomesLideres = it },
                label = { Text(text = "1. Cabeças de Chave (Líderes) - (Separar por ;)") },
                placeholder = { Text(text = "Ex: Marcelo; Thiago; Carlos; ...") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nomesComuns,
                onValueChange = { nomesComuns = it },
                label = { Text(text = "2. Demais Membros - (Separar por ;)") },
                placeholder = { Text(text = "Ex: Ana; Bruno; Diogo; Felipe; ...") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 250.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onSortearClicked(nomesLideres, nomesComuns) },
                enabled = isEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sortear Grupos de $TAMANHO_DO_GRUPO")
            }

            if (isEnabled.not()) {
                Text(
                    text = "Mínimo para sortear: 1 Líder e ${TAMANHO_DO_GRUPO - 1} Membros.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}