# Corretora-RMI

Sistema distribuído de corretora de ações desenvolvido com **Java RMI**, implementando comunicação cliente-servidor com suporte a **callbacks em tempo real**. Feito como Avaliação-1 para a disciplina de Sistemas Distribuídos.

## 📋 Descrição

O sistema simula uma corretora de ações onde um servidor centraliza o gerenciamento das ações (BTC, ETH, SOL, etc.) e múltiplos clientes se conectam remotamente para consultar e atualizar preços. Quando o preço de uma ação é atualizado, todos os clientes conectados recebem uma **notificação automática** via callback RMI.

## 🗂️ Estrutura dos Arquivos

| Arquivo | Descrição |
|---|---|
| `Servidor.java` | Servidor RMI com interface de linha de comando para gerenciar ações |
| `Cliente.java` | Cliente RMI com reconexão automática e recebimento de notificações |
| `CRUDRemote.java` | Interface remota que define as operações disponíveis no servidor |
| `ClienteRemote.java` | Interface remota do cliente, usada para receber callbacks |
| `CRUDImpl.java` | Implementação da interface `CRUDRemote` |
| `Acao.java` | Modelo de dados representando uma ação (nome + valor) |
| `Alerts.java` | Gerencia a lista de clientes e dispara notificações de broadcast |

## ▶️ Como Executar

### 1. Compilar
```bash
javac *.java
```

### 2. Iniciar o Servidor
```bash
java Servidor
```
O servidor iniciará na porta `1099` com as ações BTC, ETH e SOL pré-cadastradas.

### 3. Iniciar o Cliente
```bash
java Cliente
```
> **Obs:** Ajuste o IP do servidor em `Cliente.java` (linha 81) conforme necessário.

## 💻 Comandos Disponíveis

### Servidor e Cliente
| Comando | Descrição |
|---|---|
| `help` | Exibe a lista de comandos |
| `list` | Lista todas as ações cadastradas |
| `update` | Atualiza o preço de uma ação |
| `only` | Exibe o preço de uma ação específica |
| `add` *(apenas servidor)* | Cadastra uma nova ação |
| `closed` | Encerra a conexão / fecha o servidor |

## ⚙️ Funcionalidades

- **Comunicação remota** via Java RMI
- **Callbacks em tempo real**: clientes são notificados ao atualizar ou adicionar ações
- **Reconexão automática**: o cliente tenta se reconectar automaticamente em caso de falha (até 4 tentativas)
- **Remoção de clientes mortos**: clientes desconectados são removidos da lista automaticamente

## 🛠️ Requisitos

- Java 8 ou superior
- Nenhuma dependência externa
