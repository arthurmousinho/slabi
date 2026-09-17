# Slabi

> Sistema de gestão para pequenas e médias construtoras.

O **Slabi** é uma aplicação web para centralizar a gestão de obras, clientes, fornecedores, materiais, equipes e informações financeiras de pequenas e médias construtoras.

O projeto também tem como objetivo servir como uma aplicação prática para explorar o ecossistema **Java + Spring Boot + Angular**, aplicando conceitos de desenvolvimento de software, arquitetura, testes, segurança e boas práticas.

## Objetivos

O Slabi busca facilitar o gerenciamento operacional de construtoras, permitindo acompanhar uma obra desde seu planejamento até sua execução.

Principais objetivos:

- Centralizar informações das obras;
- Gerenciar clientes e fornecedores;
- Controlar materiais e estoque;
- Acompanhar etapas das obras;
- Gerenciar equipes;
- Controlar orçamentos e despesas;
- Disponibilizar indicadores de acompanhamento;
- Garantir autenticação e controle de acesso;
- Manter histórico das principais operações do sistema.

## Status

**Em desenvolvimento.**

O projeto será desenvolvido incrementalmente, começando por um MVP e evoluindo conforme novos módulos e necessidades forem implementados.

## Módulos

A aplicação será organizada em módulos de domínio.

### MVP

- [ ] Autenticação
- [ ] Usuários
- [ ] Clientes
- [ ] Obras
- [ ] Fornecedores
- [ ] Materiais

### Evolução

- [ ] Etapas da obra
- [ ] Equipes
- [ ] Orçamentos
- [ ] Compras
- [ ] Estoque
- [ ] Despesas
- [ ] Contas a pagar
- [ ] Contas a receber
- [ ] Dashboard
- [ ] Relatórios
- [ ] Notificações
- [ ] Auditoria

# Arquitetura

O Slabi será inicialmente desenvolvido como um **Modular Monolith**.

A aplicação terá um único backend Spring Boot, mas seus domínios serão separados em módulos independentes.

```text
                         ┌─────────────────┐
                         │     Angular     │
                         │    Web App      │
                         └────────┬────────┘
                                  │
                                HTTP
                                  │
                                  ▼
                    ┌─────────────────────────┐
                    │       Spring Boot       │
                    │                         │
                    │  ┌───────────────────┐  │
                    │  │ Authentication    │  │
                    │  ├───────────────────┤  │
                    │  │ Clients           │  │
                    │  ├───────────────────┤  │
                    │  │ Projects          │  │
                    │  ├───────────────────┤  │
                    │  │ Materials         │  │
                    │  ├───────────────────┤  │
                    │  │ Suppliers         │  │
                    │  ├───────────────────┤  │
                    │  │ Teams             │  │
                    │  └───────────────────┘  │
                    └────────────┬────────────┘
                                 │
                                 ▼
                         ┌───────────────┐
                         │  PostgreSQL   │
                         └───────────────┘
```

A adoção de uma arquitetura distribuída não faz parte da primeira versão do projeto. Tecnologias como Redis e RabbitMQ poderão ser introduzidas posteriormente quando houver necessidades reais que justifiquem seu uso.

# Stack

## Backend

| Tecnologia        | Utilização             |
| ----------------- | ---------------------- |
| Java 21           | Linguagem              |
| Spring Boot       | Framework principal    |
| Spring Web        | API REST               |
| Spring Data JPA   | Persistência           |
| Hibernate         | ORM                    |
| Spring Security   | Segurança              |
| JWT               | Autenticação           |
| Bean Validation   | Validação              |
| Flyway            | Database migrations    |
| MapStruct         | Mapeamento DTO/Entity  |
| Lombok            | Redução de boilerplate |
| JUnit 5           | Testes                 |
| Mockito           | Testes unitários       |
| Testcontainers    | Testes de integração   |
| Springdoc OpenAPI | Documentação da API    |
| Maven             | Build e dependências   |

## Frontend

| Tecnologia         | Utilização          |
| ------------------ | ------------------- |
| Angular            | Framework           |
| TypeScript         | Linguagem           |
| Angular Router     | Navegação           |
| Angular HttpClient | Comunicação com API |
| Reactive Forms     | Formulários         |
| Tailwind CSS       | Estilização         |
| Angular CDK        | Utilitários de UI   |
| Lucide Angular     | Ícones              |

## Infraestrutura

| Tecnologia     | Utilização      |
| -------------- | --------------- |
| Docker         | Containerização |
| Docker Compose | Ambiente local  |
| PostgreSQL     | Banco de dados  |

# Estrutura do projeto

O repositório será dividido entre frontend e backend.

```text
slabi/
│
├── backend/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   └── resources/
│       │
│       └── test/
│
├── frontend/
│   ├── package.json
│   └── src/
│
├── docker-compose.yml
├── .gitignore
└── README.md
```

# Estrutura do Backend

O backend será organizado principalmente por domínio.

```text
backend/
└── src/
    └── main/
        └── java/
            └── com/
                └── slabi/
                    │
                    ├── shared/
                    │   ├── config/
                    │   ├── exception/
                    │   └── security/
                    │
                    ├── authentication/
                    │   ├── controller/
                    │   ├── dto/
                    │   ├── service/
                    │   └── ...
                    │
                    ├── client/
                    │   ├── controller/
                    │   ├── dto/
                    │   ├── entity/
                    │   ├── repository/
                    │   └── service/
                    │
                    ├── project/
                    │   ├── controller/
                    │   ├── dto/
                    │   ├── entity/
                    │   ├── repository/
                    │   └── service/
                    │
                    ├── material/
                    │   ├── controller/
                    │   ├── dto/
                    │   ├── entity/
                    │   ├── repository/
                    │   └── service/
                    │
                    └── supplier/
                        ├── controller/
                        ├── dto/
                        ├── entity/
                        ├── repository/
                        └── service/
```

A ideia é manter cada domínio responsável por suas próprias regras, evitando uma estrutura global baseada somente em `controllers`, `services` e `repositories`.

# Banco de dados

O banco principal será o **PostgreSQL**.

As alterações no schema serão controladas através do **Flyway**.

Exemplo:

```text
resources/
└── db/
    └── migration/
        ├── V1__create_users.sql
        ├── V2__create_clients.sql
        └── V3__create_projects.sql
```

As migrations serão versionadas junto ao código para garantir que diferentes ambientes possam reproduzir a mesma estrutura de banco.

# Autenticação

O sistema utilizará autenticação baseada em **JWT**.

Fluxo inicial:

```text
┌──────────────┐
│    Angular   │
└──────┬───────┘
       │
       │ POST /auth/login
       ▼
┌────────────────┐
│  Spring Boot   │
└───────┬────────┘
        │
        ├── Validate credentials
        │
        └── Generate JWT
                 │
                 ▼
          ┌─────────────┐
          │   Angular   │
          └──────┬──────┘
                 │
                 │ Authorization: Bearer <token>
                 ▼
          Protected API
```

O controle de autorização será baseado nos papéis dos usuários.

Exemplo:

```text
ADMIN
MANAGER
EMPLOYEE
```

Os papéis poderão evoluir conforme os requisitos do sistema.

# Principais entidades

O domínio inicial será baseado nas seguintes entidades:

```text
User
 │
 ├───────────────┐
 │               │
 ▼               ▼
Client         Project
                  │
          ┌───────┼────────┐
          │       │        │
          ▼       ▼        ▼
       Stage   Material   Team
                  │
                  ▼
               Supplier
```

### User

Representa um usuário que possui acesso ao sistema.

### Client

Representa o cliente para quem uma obra está sendo realizada.

### Project

Representa uma obra administrada pela construtora.

### Stage

Representa uma etapa da execução da obra.

### Material

Representa materiais utilizados nas obras.

### Supplier

Representa fornecedores de materiais ou serviços.

### Team

Representa equipes ou profissionais envolvidos na execução das obras.

# Fluxo principal

O fluxo principal do sistema será baseado na criação e acompanhamento de obras.

```text
Cliente
   │
   ▼
Criar obra
   │
   ▼
Definir orçamento
   │
   ▼
Criar etapas
   │
   ▼
Definir materiais
   │
   ▼
Definir equipe
   │
   ▼
Executar obra
   │
   ├── Registrar despesas
   ├── Registrar materiais
   ├── Atualizar etapas
   └── Acompanhar progresso
   │
   ▼
Concluir obra
```

# Estratégia de testes

O projeto terá diferentes níveis de testes.

### Testes unitários

Utilizando:

- JUnit 5
- Mockito

Exemplo:

```text
Service
  │
  ├── regra válida
  ├── regra inválida
  ├── entidade inexistente
  └── erro de negócio
```

### Testes de integração

Serão utilizados:

- Spring Boot Test
- Testcontainers
- PostgreSQL

O objetivo é testar a integração real entre aplicação e banco de dados.

```text
Test
 │
 ▼
Spring Boot
 │
 ▼
Testcontainers
 │
 ▼
PostgreSQL
```

# Documentação da API

A API REST será documentada utilizando **OpenAPI**.

A documentação deverá apresentar:

- endpoints;
- parâmetros;
- request bodies;
- responses;
- códigos HTTP;
- autenticação;
- modelos de dados.

# Boas práticas

Durante o desenvolvimento serão consideradas:

- validação de entrada;
- tratamento global de exceções;
- separação de responsabilidades;
- DTOs para comunicação da API;
- migrations versionadas;
- controle de acesso;
- logs;
- testes automatizados;
- configuração por variáveis de ambiente;
- princípios SOLID;
- código orientado ao domínio;
- documentação das decisões arquiteturais.

# Roadmap

## Fase 01 — Setup

- [ ] Criar projeto Spring Boot
- [ ] Configurar Maven
- [ ] Configurar PostgreSQL
- [ ] Configurar Docker Compose
- [ ] Configurar Flyway
- [ ] Criar estrutura inicial
- [ ] Configurar tratamento de exceções
- [ ] Configurar OpenAPI

## Fase 02 — Usuários

- [ ] User entity
- [ ] Repository
- [ ] Service
- [ ] DTOs
- [ ] CRUD
- [ ] Testes

## Fase 03 — Clientes

- [ ] Client entity
- [ ] CRUD
- [ ] Validações
- [ ] Testes
- [ ] Integração com User

## Fase 04 — Obras

- [ ] Project entity
- [ ] CRUD
- [ ] Relacionamento com Client
- [ ] Status da obra
- [ ] Datas
- [ ] Orçamento inicial
- [ ] Testes

## Fase 05 — Frontend

- [ ] Criar aplicação Angular
- [ ] Layout
- [ ] Login
- [ ] Dashboard
- [ ] Clientes
- [ ] Obras
- [ ] Formulários
- [ ] Integração com API

## Fase 06 — Gestão da obra

- [ ] Etapas
- [ ] Materiais
- [ ] Fornecedores
- [ ] Equipes
- [ ] Despesas
- [ ] Progresso da obra

## Fase 07 — Evolução

- [ ] Redis
- [ ] RabbitMQ
- [ ] Notificações
- [ ] Auditoria
- [ ] Relatórios
- [ ] Dashboard avançado

# Ambiente de desenvolvimento

O ambiente local utilizará Docker Compose para executar os serviços necessários.

Inicialmente:

```text
┌─────────────────────────────┐
│       Docker Compose        │
│                             │
│  ┌───────────────────────┐  │
│  │      PostgreSQL       │  │
│  │        :5432          │  │
│  └───────────────────────┘  │
│                             │
└─────────────────────────────┘
```

O backend e frontend poderão ser executados diretamente durante o desenvolvimento para facilitar o hot reload.

# Interface

A interface do Slabi seguirá uma abordagem:

- minimalista;
- profissional;
- responsiva;
- orientada a dashboards;
- foco em produtividade;
- componentes reutilizáveis.

O Angular será responsável pela aplicação web, enquanto o Spring Boot fornecerá a API REST.

# Princípios do projeto

O desenvolvimento do Slabi seguirá alguns princípios:

> **Começar simples e evoluir conforme a necessidade.**

> **Preferir modularidade antes de microservices.**

> **Regras de negócio devem estar protegidas por testes.**

> **Infraestrutura deve servir ao domínio, e não o contrário.**

> **Evitar adicionar tecnologias apenas por adicionar.**

## Licença

Projeto desenvolvido para fins de estudo, portfólio e experimentação com Java, Spring Boot e Angular.
