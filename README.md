# SorteioPro | Sorteio de Grupos Inteligente

🎲 Um aplicativo Android nativo, construído com **Kotlin** e **Jetpack Compose**, para automatizar o sorteio e a formação de equipes de forma controlada.

Ideal para professores, treinadores ou gerentes que precisam dividir um grande grupo, garantindo que cada equipe tenha um líder ou um membro chave específico.

## ✨ Funcionalidades

Este projeto está dividido em duas telas (usando Navigation-Compose) e oferece as seguintes funcionalidades principais:

1.  **Tamanho do Grupo Flexível:**
    * O usuário pode **definir o número exato de pessoas** que cada grupo deverá ter, tornando o aplicativo adaptável a qualquer necessidade (seja grupos de 3, 4, 5, etc.).

2.  **Entrada de Dados Separada (Tela Principal):**
    * **Líderes (Cabeças de Chave):** Um campo para a lista de nomes que devem obrigatoriamente iniciar cada grupo.
    * **Demais Membros:** Um campo para a lista de pessoas que completarão os grupos.
    * **Layout Otimizado:** Os campos de texto ocupam o espaço disponível da tela de forma igual, facilitando a visualização e edição de longas listas de nomes.

3.  **Sorteio Controlado:**
    * Prioriza a formação do maior número possível de grupos, usando o **tamanho definido pelo usuário**.
    * Cada grupo formado recebe **exatamente um Líder** (Cabeça de Chave), e os membros restantes são sorteados aleatoriamente da lista de Demais Membros.

4.  **Resultado Transparente (Tela de Resultados):**
    * Exibe claramente todos os grupos formados, listando seus membros.
    * Informa quais nomes **sobraram** (seja um Líder que não pôde formar um grupo completo ou Demais Membros não utilizados).

## 🛠️ Tecnologias Utilizadas

Este projeto demonstra o uso de práticas modernas de desenvolvimento Android:

* **Linguagem:** Kotlin
* **UI/Design:** Jetpack Compose
* **Navegação:** Navigation-Compose (gerenciamento de Múltiplas Telas)
* **Gerenciamento de Layout:** Uso de `Modifier.weight()` para layouts adaptáveis e otimizados.
* **Gerenciamento de Texto:** Uso de `stringResources` para todas as strings literais, facilitando a localização e manutenção.
* **Lógica Funcional:** Uso eficiente de funções de coleção do Kotlin (`.shuffled()`, `.take()`, `.drop()`) para a lógica de sorteio.

## 🚀 Como Executar

1.  Clone o repositório: https://github.com/gfreiresantos95/SorteioPro
2.  Abra a pasta do projeto no Android Studio.
3.  Sincronize o Gradle.
4.  Execute em um emulador ou dispositivo.