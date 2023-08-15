# [![Integração Continua com Github Actions](https://github.com/Luisfelipeqt/projeto-auto-escola/actions/workflows/deploy.yml/badge.svg)](https://github.com/Luisfelipeqt/projeto-auto-escola/actions/workflows/deploy.yml)


# Projeto Exemplo com Java 17, Docker, Spring Boot e Swagger

Este é um projeto exemplo que demonstra como configurar uma aplicação usando Java 17, Docker, Spring Boot e Swagger.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java Development Kit (JDK) 17
- Docker

## Executando o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/Luisfelipeqt/projeto-auto-escola.git
cd seu-projeto

2. Build e Execução com Docker

# Crie a imagem Docker da aplicação
docker build -t seu-projeto .

# Execute a aplicação na porta padrão 8080
docker run -p 8080:8080 seu-projeto


A aplicação Spring Boot estará acessível em: http://localhost:8080

3. Documentação Swagger
Após a execução da aplicação, você pode acessar a documentação Swagger em: http://localhost:8080/swagger-ui/index.html

Estrutura do Projeto
Explicação da estrutura do projeto:

src/: Contém o código-fonte da aplicação.
Dockerfile: Arquivo de configuração do Docker para criar a imagem da aplicação.
pom.xml: Arquivo de configuração do Maven para gerenciar as dependências do projeto.
Contribuindo
Sinta-se à vontade para contribuir com melhorias, correções de bugs ou novos recursos. Crie um fork deste repositório, faça suas alterações e envie um pull request.


