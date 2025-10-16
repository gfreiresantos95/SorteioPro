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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal(onSortearClicked: (String) -> Unit) {
    var nomes by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("Sortear Grupos") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nomes,
                onValueChange = { nomes = it },
                label = { Text("Lista de Nomes (separados por ;)") },
                placeholder = { Text("Ex: Ana; Bruno; Carlos; ...") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onSortearClicked(nomes) },
                enabled = nomes.split(";")
                    .filter { it.trim().isNotEmpty() }.size >= Sorteador.TAMANHO_DO_GRUPO,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sortear Grupos de ${Sorteador.TAMANHO_DO_GRUPO}")
            }
        }
    }
}