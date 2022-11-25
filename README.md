   # API para biblioteca

## IESB Progamação orientada a Objetos 2022

## Ambiente
API roda em Java com integração do Spring Boot pela IDE IntelliJ IdeaC, o intrumento de Build ja integrado na IDE, A api usa entradas do tipo JSON

## Membros
1. Jean Carlos Gomes Aguiar 1922130009

## Linguagem usada **Spring Boot**
## Banco de dados usado **PostgreSQL**

## Descriçao

A API foi criada para realizar operações básicas de login, registro de usuários, esquecer senha, para cadastro de livros em uma biblioteca e a busca desses livros.
As funções serão acessadas por rotas, enviadas pelo aplicativo POSTMAN.

## Testes

Testes podem ser feitos utilizando a url = [ Base URL: <https://apipoo.herokuapp.com/> ]

## Rotas
## Principal - /api

| Rotas | Função |
| ------ | ------ |
| POST - /signup  | - Cria um usuario |
| GET - /users  | - Lista usários |
| POST - /login   | - Entra em um usuario |
| POST - /forgotpass  | - Envia uma senha para o email |
| GET - /getbooks | - Lista livros |
| POST - /savebook | - Salva um novo livro |
| DELETE - /delbook/{id} | - Deleta um livro |
| DELETE - /delbooks | - Deleta todos os livros |

## Sintaxe do JSON para criar objetos

## Usuario:

email: String,

password: String

## Livro:

title: String,

author: String,

publisher: String,

pages: Int,

genre: String

## Sintaxe de Login:

email: String,

password: String

## Sintaxe de Esquecer senha

email: String
