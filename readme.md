Mobile
=============

Sistema para recebimento de notificações da UFG

***

#### Objetivo
Um aplicativo plataforma Android onde seja possível gerenciar notificações que seriam enviadas pela UFG.



***

### Sobre o projeto:

#### Arquitetura

* Java, como plataforma geral de desenvolvimento
* Android SDK, como principal API de desenvolvimento.
* SQLite, como banco de dados.
* ORMLite, como solução de persistência.

#### Como utilizar

Ao abrir o aplicativo, temos 2 opções: Notificações Publicas e Login.

Notificações Públicas: Lista com as notificações gerais recebidas, não precisa estar logado para verificar.

Login: Entrar com matricula e senha (no caso, utilizar o usuário admin/admin). Feito isso, irá para a tela do Usuário. Temos 2 opções: Ativar Disciplinas, Notificações e Logout.

Ativar Disciplinas: As disciplinas cadastradas ficam listadas para você selecionar quais poderão ser recebidas.

Notificações: Lista com as notificações direcionadas ao usuário.

Logout: Fazer o logout do usuário, removendo o mesmo da sessão.

Nas telas de Notificação, ao clicar com o botão de menu, são dadas as opções de ordenação (Data ou Remetente, Crescente ou Decrescente).

Na tela de Notificações do usuário, é possível, ao dar um clique longo na mensagem, marcar como não lida.

Em ambas, ao clicar na mensagem, é direcionada a uma nova tela com o texto da mensagem.

```
Autor: Douglas Tavares de Azevedo Japiassu
* Graduando em Engenharia de Software pela UFG
```
