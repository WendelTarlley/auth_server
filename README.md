# Auth Server

## Descrição

O Auth Server é uma aplicação Spring Boot projetada para fornecer funcionalidades de autenticação e autorização. Ele permite a criação de usuários, realiza login e fornece um token JWT (JSON Web Token) para autenticação. Além disso, a API valida tokens JWT gerados para garantir a segurança e a integridade das requisições.

## Funcionalidades

- **Criação de Usuários**: Endpoint para registrar novos usuários.
- **Login**: Endpoint para autenticação de usuários e geração de tokens JWT.
- **Validação de Token**: Endpoint para verificar a validade dos tokens JWT gerados.

## Tecnologias

- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- H2 Database (ou qualquer outro banco de dados configurado)

## Endpoints

###  Criar Usuário

- **URL**: `/auth/register`
- **Método**: `POST`
- **Descrição**: Cria um novo usuário com a Role padrão User

  **Request Body**:
```
  {
    "username": "string",
    "password": "string"
    "email": "string"
  }
```
  **Resposta**: Status `201 Created` se bem-sucedido, com detalhes do usuário criado.

###  Login e Obter Token

- **URL**: `/auth/login`
- **Método**: `POST`
- **Descrição**: Realiza login e retorna um token JWT.

  **Request Body**:
```
  {
    "username": "string",
    "password": "string"
  }
```
  **Resposta**: Status `200 OK` com o token JWT no corpo da resposta.

###  Validar Token

- **URL**: `/api/auth/validate`
- **Método**: `POST`
- **Descrição**: Valida o token JWT fornecido.

  **Request Body**:
```
  {
    "token": "string"
  }
```
  **Resposta**: Status `200 OK` e o usuário que está validando, se o token for válido, `401 Unauthorized` se o token for inválido ou expirado.

## Configuração

### 1. Clonando o Repositório
```
git clone https://github.com/seu-usuario/auth-server.git  
cd auth-server
```
### 2. Configurando o Banco de Dados

O `application.properties` está configurado para usar um banco de dados MySQL por padrão. Para utilizar outro banco de dados, ajuste as configurações em `src/main/resources/application.properties`.

### 3. Executando a Aplicação
```
./mvnw spring-boot:run

Ou, se você estiver usando o Gradle:

./gradlew bootRun
```
## Testes

A aplicação inclui testes unitários e de integração. Para executá-los, use:

### Maven:
```
./mvnw test
```
### Gradle:
```
./gradlew test
```
## Contribuindo

Sinta-se à vontade para contribuir com melhorias ou correções! Abra uma issue ou envie um pull request para o repositório.
