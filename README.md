# 📚 Gerenciamento Biblioteca API

API RESTful desenvolvida com Java e Spring Boot para gerenciamento de autores, obras e usuários, utilizando autenticação JWT, controle de permissões por roles, Docker, PostgreSQL e deploy em produção.

---

# 🚀 Deploy Online

### 🔗 Swagger

https://gerenciamento-biblioteca-v3cl.onrender.com/swagger-ui/index.html

---

# 🛠️ Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Security
* JWT Authentication
* Refresh Token
* PostgreSQL
* Docker
* Docker Compose
* Swagger / OpenAPI
* JUnit 5
* Mockito
* Gradle
* Render (Deploy)

---

# 📋 Funcionalidades

## 👤 Usuários

* Cadastro de usuários
* Login com JWT
* Refresh Token
* Controle de acesso por roles:

  * ADMIN
  * USER

---

## ✍️ Autores

* Criar autor
* Buscar autor por ID
* Atualizar autor parcialmente
* Deletar autor

### Regras de negócio:

* CPF obrigatório para autores brasileiros

---

## 📚 Obras

* Criar obra
* Buscar obra por ID
* Atualizar obra parcialmente
* Deletar obra

### Regras de negócio:

* Deve possuir data de publicação ou exposição
* Relacionamento ManyToMany com autores

---

# 🔐 Segurança

A aplicação utiliza:

* Spring Security
* JWT Authentication
* Refresh Token
* Roles e autorização por endpoint
* Senhas criptografadas com BCrypt

---

# 🧪 Testes

Foram implementados:

✅ Testes Unitários
✅ Testes de Controller
✅ Mockito
✅ MockMvc

---

# 🐳 Docker

A aplicação possui configuração completa com:

* Dockerfile
* Docker Compose
* PostgreSQL containerizado

### Executar localmente:

```bash
docker compose up --build
```

---

# 📂 Estrutura do Projeto

```bash
src
 ┣ autor
 ┣ obra
 ┣ usuario
 ┣ security
 ┣ exceptions
 ┗ infrastructure
```

---

# 🔑 Autenticação JWT

## Login

```http
POST /usuario/login
```

### Exemplo:

```json
{
  "email": "admin@gmail.com",
  "senha": "123456"
}
```

---

## Resposta

```json
{
  "accessToken": "jwt-token",
  "refreshToken": "refresh-token"
}
```

---

# 👮 Roles

## ADMIN

Pode:

* criar obras
* atualizar obras
* deletar obras

---

## USER

Pode:

* visualizar obras

---

# 📦 Como Executar Localmente

## Clonar projeto

```bash
git clone https://github.com/aureoandradedev/gerenciamento-biblioteca.git
```

---

## Entrar na pasta

```bash
cd gerenciamento-biblioteca
```

---

## Rodar aplicação

```bash
docker compose up --build
```

---

# 📖 Swagger

Após iniciar a aplicação:

```bash
http://localhost:8089/swagger-ui/index.html
```

---

# 👨‍💻 Autor

### Áureo Andrade

* GitHub:
  https://github.com/aureoandradedev

* LinkedIn:
 https://www.linkedin.com/in/aureoandrade/

---

# ⭐ Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

* aprofundamento em backend Java
* arquitetura REST
* autenticação JWT
* segurança com Spring Security
* containerização com Docker
* deploy em produção
* boas práticas de desenvolvimento
* testes automatizados

---
