# Campo Minado (Minesweeper) - Projeto Java

## Descrição

Este projeto é uma implementação do clássico jogo **Campo Minado** em Java, executado via console. O objetivo é desenvolver uma versão funcional e orientada a objetos do jogo, explorando conceitos de programação moderna como **programação orientada a objetos (POO)**, **uso de exceções**, **streams e programação funcional** e **design modular**.

## Estrutura do Projeto

### Pacotes

- **`com.kossatzdev.cm`** - Pacote raiz com a classe principal `Aplication` para inicialização do jogo.

- **`com.kossatzdev.cm.model`** - Contém as classes do modelo do jogo:
  - `Board`: Representa o tabuleiro, gerencia campos, minas, abertura de casas e verificação do objetivo.
  - `Field`: Representa um campo individual no tabuleiro, mantendo seu estado (aberto, minado, marcado) e relacionamentos com vizinhos.

- **`com.kossatzdev.cm.excecao`** - Exceções customizadas usadas para controle de fluxo do jogo:
  - `ExplosionException`: Lançada quando um campo minado é aberto.
  - `ExitException`: Lançada quando o usuário digita "sair".

- **`com.kossatzdev.cm.view`** - Implementação da interface de usuário em console (classe `Console`).

## Paradigmas e Metodologias Utilizadas

### 1. Programação Orientada a Objetos (POO)

- **Encapsulamento:** A lógica e estado de cada campo e do tabuleiro são encapsulados nas classes `Field` e `Board`. O acesso direto aos atributos é restrito e controlado via métodos públicos e pacotes.

- **Abstração:** O projeto separa claramente o modelo do jogo (tabuleiro, campos) da interface (console), facilitando manutenção e extensão futura.

- **Coesão e Responsabilidade Única:** Cada classe tem responsabilidade clara e única:
  - `Field` gerencia estado individual do campo, vizinhos e regras locais.
  - `Board` gerencia a estrutura do tabuleiro, operações globais, posicionamento das minas, e regras do jogo.

- **Polimorfismo:** Embora não tenha herança complexa aqui, as exceções personalizadas permitem tratamento polimórfico de erros.

### 2. Programação Funcional e Streams

- Uso de **Streams Java 8+** para manipulação e busca de campos, por exemplo, para abrir ou marcar campos, e para contar minas no tabuleiro (`filter`, `allMatch`, `forEach`, `parallelStream`).

- Uso de expressões lambda e Predicates (`Predicate<Field> mined = c->c.isMined();`) para tornar o código mais expressivo e declarativo.

### 3. Tratamento de Exceções como Controle de Fluxo

- Exceção `ExplosionException` é usada para sinalizar a perda do jogo, interrompendo o fluxo normal quando um campo minado é aberto, o que desencadeia a abertura de todos os campos para mostrar o estado final.

### 4. Design Modular

- Separação de pacotes e responsabilidades facilita manutenção, testes e possíveis futuras extensões (ex: interface gráfica, diferentes modos de jogo).

### 5. Paralelismo

- Uso de `parallelStream()` para potencialmente melhorar a performance na manipulação de campos em operações que podem ser paralelizadas (abrir, marcar campos).

## Detalhes Técnicos e Funcionalidades

- **Geração do tabuleiro:** O tabuleiro é inicializado com dimensões e quantidade de minas configuráveis.

- **Vizinhança:** Cada campo conhece seus vizinhos (inclusive diagonais), permitindo abertura automática em cascata de campos seguros.

- **Marcação:** Campos podem ser marcados como suspeitos de conter minas, impedindo abertura acidental.

- **Objetivo do jogo:** O método `objectiveAchieved()` verifica se todos os campos seguros foram abertos e todas as minas corretamente marcadas.

- **Reset do jogo:** Permite reiniciar o tabuleiro, limpando estados e redistribuindo minas aleatoriamente.

## Possíveis Melhorias Futuras

- Implementar interface gráfica para melhorar a experiência do usuário.
- Adicionar suporte para diferentes níveis de dificuldade.
- Implementar timer e pontuação.
- Incluir mais testes automatizados para garantir robustez.

## Como Executar

1. Compile o projeto Java (JDK 8+ recomendado).
2. Execute a classe principal `com.kossatzdev.cm.Aplication`.
3. O jogo inicia no console, permitindo interação básica via texto.

## Exemplo Terminal

- Digite o valor da (linha,coluna) ou 'sair': 2,2
- Digite: 1 - Abrir Campo, 2 - Marcar Campo, ou 'sair': 1
```
  0 1 2 3 4 5
0 ? ? ? ? ? ?
1 ? ? ? ? ? ?
2 ? ?   ? ? ?
3 ? ? ? ? ? ?
4 ? ? ? ? ? ?
5 ? ? ? ? ? ?
```

## Licença

Este projeto está disponível sob a licença MIT. Sinta-se à vontade para usar, modificar e contribuir.
