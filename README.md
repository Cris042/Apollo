# Treino API

Este projeto implementa uma API REST para gerenciamento de rotinas de treino, seguindo os princípios da arquitetura hexagonal e utilizando Java com o framework Quarkus. O projeto inclui autenticação e autorização com Keycloak e persistência de dados no PostgreSQL.

## Funcionalidades

- **Autenticação e autorização:** Implementado com Keycloak.
- **Gestão de rotinas:** CRUD de rotinas de treino com informações detalhadas de exercícios.
- **Cálculo de Volume Load:** Cálculo automático do volume load ao finalizar uma rotina.
- **Histórico de Rotinas:** Armazena informações sobre rotinas finalizadas, incluindo volume load, data de início e fim.

## Dependências

As principais dependências utilizadas no projeto incluem:

- `quarkus-resteasy-reactive`
- `quarkus-hibernate-orm-panache`
- `quarkus-jdbc-postgresql`
- `quarkus-keycloak-authorization`
- `quarkus-smallrye-openapi`
- `docker`
- `keycloak`
- `postgresql`

## Estrutura do Projeto

A estrutura do projeto segue os princípios da arquitetura hexagonal:

```
├── config
├── core
│   ├── port
│   │   ├── in
│   │   └── out
│   ├── domain  
│   │   └── models
│   │   └── repository
│   │   
│   └── usecase
├── adapters
│   ├── adapters
│   │   ├── web
```

## Endpoints da API

### Autenticação
- A autenticação é gerenciada pelo Keycloak.

### Rotinas
- **GET /api/routines?userId={userId}:** Lista todas as rotinas de um usuário.
- **POST /api/routines:** Cria uma nova rotina.
- **PUT /api/routines/{id}:** Atualiza uma rotina existente.
- **DELETE /api/routines/{id}?userId={userId}:** Remove uma rotina.
- **POST /api/routines/{id}/complete?startTime={startTime}?endTime={endTime}:** Finaliza uma rotina e calcula o volume load.

## Como Executar

### Pré-requisitos

- Docker e Docker Compose
- Java 21 
- Maven

