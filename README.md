# Apollo

Este projeto implementa uma API REST para gerenciamento de rotinas de treino, seguindo os princípios da arquitetura hexagonal e utilizando Java com o framework Quarkus. O projeto inclui autenticação e autorização com Keycloak e persistência de dados no PostgreSQL.

## Funcionalidades

- **Autenticação e autorização:** Implementado com Keycloak.
- **Gestão de rotinas:** CRUD de rotinas de treino com informações detalhadas de exercícios.
- **Cálculo de Volume Load:** Cálculo automático do volume load ao finalizar uma rotina.
- **Histórico de Rotinas:** Armazena informações sobre rotinas finalizadas, incluindo volume load, data de início e fim.

## Dependências

As principais dependências utilizadas no projeto incluem:

- `docker`
- `keycloak`
- `postgresql`

## Estrutura do Projeto

A estrutura do projeto segue os princípios da arquitetura hexagonal:

```
├── infrastructure
├── domain
│   ├── entities
│
├── application
│   ├── port
│   │   ├── in
│   │   └── out
│   ├── service 
│   │   
├── adapters
│   ├── in
│   │    ├── web
│   ├── out
│   │    ├── repository
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

### Exemplo da Criação da Rotina

**Body:**
```json
{
  "name": "Rotina de Treino - Segunda-feira",
  "userId": "7aDA2b3C-e3dA-Aacf-C470-7CD25dB2b7c7",
  "exercises": [
    {
      "name": "Supino Reto",
      "repetitions": 10,
      "load": 80,
      "restTime": 60,
      "series": 4
    },
    {
      "name": "Agachamento Livre",
      "repetitions": 12,
      "load": 100,
      "restTime": 90,
      "series": 3
    },
    {
      "name": "Remada Curvada",
      "repetitions": 10,
      "load": 70,
      "restTime": 60,
      "series": 4
    }
  ]
}
```

## Como Executar

### Passos

1. Inicie a aplicação:
   ```bash
   mvnw quarkus:dev
   ```

### Pré-requisitos

- Docker
- Java 21 
- Maven

### Doc

- http://localhost:8080/q/swagger-ui/

