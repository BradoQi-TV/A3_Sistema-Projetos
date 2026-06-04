# Sistema de Gerenciamento de Projetos e Equipes

Este projeto foi desenvolvido em Java como parte da atividade A3, com o objetivo de criar um sistema simples para gerenciar usuários, equipes, projetos e tarefas.

A ideia principal do sistema é permitir o cadastro e acompanhamento de projetos, relacionando equipes, usuários responsáveis e tarefas com seus respectivos status.

## Objetivo do projeto

O sistema tem como objetivo simular uma aplicação de gestão de projetos, onde é possível:

- Cadastrar usuários
- Cadastrar equipes
- Cadastrar projetos
- Cadastrar tarefas
- Vincular equipes a projetos
- Adicionar membros às equipes
- Alterar status de tarefas
- Alterar status de projetos
- Listar informações gerais do sistema
- Gerar um relatório geral de acompanhamento

## Tecnologias utilizadas

- Java
- Eclipse IDE
- Git e GitHub

## Estrutura do projeto

Durante o desenvolvimento, a classe "Main" começou concentrando praticamente toda a lógica do sistema. Depois, o projeto foi refatorado para ficar mais organizado e mais próximo das boas práticas de programação orientada a objetos.

Atualmente, o sistema está dividido em classes principais, serviços e utilitários.

### Classes principais

As classes principais representam os elementos do sistema:

- "Usuario"
- "Equipe"
- "Projeto"
- "Tarefa"

Também foram criados enums para representar os tipos e status utilizados:

- "PerfilUsuario"
- "StatusProjeto"
- "StatusTarefa"

### Pacote service

O pacote "service" concentra as regras e operações principais do sistema:

- "UsuarioService"
- "EquipeService"
- "ProjetoService"
- "TarefaService"
- "RelatorioService"

Essa separação foi feita para deixar a "Main" mais limpa e facilitar a manutenção do código.

### Pacote util

O pacote "util" contém a classe:

- "EntradaUtil"

Ela é responsável por métodos de leitura e validação de entrada, como leitura de números, datas, textos obrigatórios, e-mail e CPF.

## Funcionalidades do menu

O sistema possui um menu interativo com as seguintes opções:

1 - Listar usuários  
2 - Listar equipes  
3 - Listar projetos  
4 - Listar tarefas  
5 - Listar tarefas por status  
6 - Listar membros de uma equipe  
7 - Listar equipes de um projeto  
8 - Listar projetos de uma equipe  
9 - Exibir detalhes de uma tarefa  
10 - Alterar status da tarefa  
11 - Alterar status do projeto  
12 - Cadastrar nova tarefa  
13 - Cadastrar novo projeto  
14 - Listar tarefas de um projeto  
15 - Cadastrar novo usuário  
16 - Cadastrar nova equipe  
17 - Adicionar membro à equipe  
18 - Vincular equipe a projeto  
19 - Relatório geral de acompanhamento  
0 - Sair

## Validações implementadas

O sistema possui algumas validações para evitar erros durante a execução, como:

- Impedir entrada de letras em campos numéricos
- Validar datas no formato "dd/MM/yyyy"
- Impedir datas anteriores à data atual em cadastros importantes
- Bloquear CPF inválido
- Bloquear CPF repetido
- Bloquear login repetido
- Impedir adicionar o mesmo usuário duas vezes na mesma equipe
- Impedir vincular a mesma equipe duas vezes ao mesmo projeto
- Verificar se o gerente escolhido realmente possui perfil de gerente

## Organização e refatoração

Uma parte importante do projeto foi a refatoração da classe "Main".

Inicialmente, a "Main" possuía muitas linhas e concentrava várias responsabilidades. Com a refatoração, o código foi separado em classes de serviço e utilitárias, deixando a "Main" responsável principalmente por iniciar o sistema, exibir o menu e chamar os métodos corretos.

Essa mudança deixou o projeto mais organizado, mais fácil de entender e mais próximo da programação orientada a objetos.

## Como executar o projeto

Para executar o projeto:

1. Baixe ou clone este repositório.
2. Abra o projeto em uma IDE Java, como o Eclipse.
3. Execute a classe "Main.java".
4. Use o menu no console para navegar pelas opções do sistema.

## Observação sobre banco de dados

No momento, o sistema ainda utiliza listas em memória para armazenar os dados durante a execução. Isso significa que os dados cadastrados não ficam salvos após fechar o programa.

Uma melhoria futura seria integrar o sistema com um banco de dados, como MySQL, para salvar usuários, equipes, projetos e tarefas de forma permanente.

## Status do projeto

O projeto está funcional e organizado, com as principais funcionalidades implementadas e testadas.
