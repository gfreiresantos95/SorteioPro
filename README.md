# 👑 SorteioPro | Sorteio de Grupos Inteligente

🎲 Um aplicativo Android nativo, construído com Kotlin e Jetpack Compose, para automatizar o sorteio e a formação de equipes de forma controlada e profissional.

Ideal para professores, treinadores ou gerentes que precisam dividir um grande grupo, garantindo que cada equipe tenha um líder ou um membro chave específico, com controle total sobre o tamanho dos grupos.

## ✨ Destaques & Funcionalidades

O projeto conta com uma experiência de usuário completa e uma lógica de sorteio altamente flexível:

### 🎨 Design e UX
1. **Tema Personalizado:**
    * Implementação de um tema completo no Jetpack Compose, garantindo um visual moderno e único.
2. **Splash Screen:**
    * Integração de uma tela de abertura moderna (usando androidx.core:core-splashscreen), que exibe a identidade visual do app durante o carregamento inicial.
3. **Layout Otimizado:**
    * Os campos de texto para entrada de nomes ocupam o espaço disponível da tela de forma igual (Modifier.weight()), facilitando a visualização e edição de longas listas.

### ⚙️ Lógica de Sorteio
1. **Tamanho do Grupo Flexível:**
    * O usuário pode definir o número exato de pessoas que cada grupo deverá ter, tornando o sorteio adaptável a qualquer necessidade (grupos de 3, 4, 5, etc.).
2. **Entrada Controlada (Tela Principal):**
    * Líderes (Cabeças de Chave): Um campo para a lista de nomes que devem obrigatoriamente iniciar cada grupo.
    * Demais Membros: Um campo para a lista de pessoas que completarão os grupos.
3. **Sorteio Inteligente:**
    * Prioriza a formação do maior número possível de grupos, usando o tamanho definido pelo usuário.
    * Cada grupo formado recebe exatamente um Líder, e os membros restantes são sorteados aleatoriamente.
4. **Resultado Transparente (Tela de Resultados):**
    * Exibe claramente todos os grupos formados e o tamanho exato de cada um.
    * Informa quais nomes sobraram (seja um Líder que não pôde formar um grupo completo ou Demais Membros não utilizados).

## 🛠️ Tecnologias Utilizadas

Este projeto demonstra o uso de práticas modernas de desenvolvimento Android:

* **Linguagem:** Kotlin 
* **UI/Design:** Jetpack Compose (Material 3)
* **Navegação:** Navigation-Compose (gerenciamento de Múltiplas Telas)
* **UX/Branding:** androidx.core:core-splashscreen 
* **Gerenciamento de Layout:** Uso de Modifier.weight() para layouts adaptáveis e otimizados. 
* **Localização/Organização:** Uso de stringResources para todas as strings literais. 
* **Lógica Funcional:** Uso eficiente de funções de coleção do Kotlin (.shuffled(), .take(), .drop()) para a lógica de sorteio.

## 🚀 Como Executar

1.  Clone o repositório: https://github.com/gfreiresantos95/SorteioPro
2.  Abra a pasta do projeto no Android Studio.
3.  Sincronize o Gradle.
4.  Execute em um emulador ou dispositivo.