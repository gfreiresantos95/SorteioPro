# 🎲 SorteioPro: Sorteio de Grupos com Líderes

Um aplicativo Android nativo, construído com Kotlin e Jetpack Compose, para automatizar o sorteio e
a formação de equipes de forma controlada e profissional.

Ideal para professores, treinadores ou gerentes que precisam dividir um grande grupo, garantindo que
cada equipe tenha um líder ou um membro chave específico, com controle total sobre o tamanho dos
grupos.

O projeto demonstra as melhores práticas de arquitetura e UI moderna da Google.

## ✨ Destaques & Funcionalidades

O projeto conta com uma experiência de usuário completa e uma lógica de sorteio altamente flexível:

### 🎨 Design e UX

1. **Tema Personalizado (Material 3):**
    * Implementação de um tema completo no Jetpack Compose, com cores customizadas e suporte a Dark
      Mode.
2. **Splash Screen:**
    * Integração de uma tela de abertura moderna (usando `androidx.core:core-splashscreen`), que
      exibe a identidade visual do app durante o carregamento inicial.
3. **Layout Adaptável:**
    * O gerenciamento de layout utiliza `Modifier.weight()` e `Scaffold` para garantir que o
      conteúdo se adapte a diferentes tamanhos de tela e resoluções.

### ⚙️ Lógica de Sorteio

1. **Tamanho do Grupo Flexível:**
    * O usuário pode definir o número exato de pessoas que cada grupo deverá ter.
2. **Entrada Controlada (Tela Principal):**
    * Líderes (Cabeças de Chave): Campo para a lista de nomes que devem obrigatoriamente iniciar
      cada grupo.
    * Demais Membros: Campo para a lista de pessoas que completarão os grupos.
3. **Sorteio Inteligente:**
    * Prioriza a formação do maior número possível de grupos, usando o tamanho definido pelo
      usuário.
    * Cada grupo formado recebe exatamente um Líder, e os membros restantes são sorteados
      aleatoriamente.
4. **Resultado Transparente (Tela de Resultados):**
    * Exibe claramente todos os grupos formados e informa quais nomes sobraram (seja um Líder ou
      Demais Membros não utilizados).

## 🛠️ Tecnologias e Arquitetura

Este projeto demonstra o uso de práticas modernas de desenvolvimento Android, seguindo os princípios
da Arquitetura Limpa (Clean Architecture).

### 💻 Stack Tecnológica

* **Linguagem:** Kotlin
* **UI/Design:** Jetpack Compose (Material 3)
* **Navegação:** Navigation-Compose
* **Gerenciamento de Estado:** ViewModel (AndroidX)
* **UX/Branding:** `androidx.core:core-splashscreen`

### 🏗️ Arquitetura de Camadas

A lógica do aplicativo é organizada em camadas separadas para máxima testabilidade e manutenção:

| Camada       | Pacotes                          | Responsabilidade                                                                               |
|:-------------|:---------------------------------|:-----------------------------------------------------------------------------------------------|
| **`ui`**     | `screens`, `theme`, `navigation` | Gerencia a visualização (`Composables`) e o estado da UI (`ViewModel`).                        |
| **`domain`** | `usecase`                        | Contém a lógica de negócio pura, como a classe `Sorteador`. Independente do Android Framework. |
| **`data`**   | `models`                         | Definição das estruturas de dados imutáveis (`ResultadoSorteio`).                              |

## 🧪 Qualidade e Testabilidade

O foco em uma arquitetura de camadas permite testes eficazes e isolados:

* **Testes Unitários:** Cobertura completa da lógica de negócio (`Sorteador`) e da gestão de
  estado (`MainViewModel`), garantindo que o algoritmo central é robusto.
* **Testes de UI:** Utilização de `ComposeTestRule` e `Modifier.testTag` para
  validar o fluxo de navegação, a interação do usuário e a exibição correta dos dados nas telas.

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/gfreiresantos95/SorteioPro
   ```
2. Abra a pasta do projeto no Android Studio.
3. Sincronize o Gradle.
4. Execute em um emulador ou dispositivo (API 24+ recomendado).