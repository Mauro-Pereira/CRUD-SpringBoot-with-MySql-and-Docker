![CI](https://github.com/Mauro-Pereira/-Learning-How-to-make-CRUD-using-Spring-Boot-with-Java-and-MySQL/actions/workflows/ci.yml/badge.svg)

# Projeto Spring Boot CRUD com MySQL e Docker

## Introdução
Neste projeto, estou utilizando Spring Boot com Java e MySQL para criar um sistema CRUD (CREATE, READ, UPDATE e DELETE). Este é um exemplo básico e fundamental que todo desenvolvedor deve conhecer. O MySQL é um sistema de gerenciamento de banco de dados relacional amplamente utilizado devido à sua robustez e facilidade de uso. 

Além disso, para tornar o projeto mais eficiente e prático, utilizei Docker, testes automatizados e integração contínua (CI). O Docker facilita a configuração e execução do ambiente, enquanto os testes automatizados garantem a qualidade do código. A integração contínua, por sua vez, permite que possamos detectar erros rapidamente, mantendo o projeto sempre em bom estado.

![Spring Boot](./screenshot/128px-Spring_Boot.svg.png)

## MVC (Model-View-Controller)
O padrão de arquitetura MVC (Model-View-Controller) é amplamente utilizado no desenvolvimento de aplicações web. Ele separa a aplicação em três componentes principais:

- **Model**: Representa a lógica de negócios e os dados da aplicação.
- **View**: É responsável pela apresentação das informações ao usuário.
- **Controller**: Lida com as interações do usuário, manipulando o modelo e atualizando a visão.

Este projeto foi desenvolvido com base neste modelo, garantindo uma separação clara de responsabilidades e facilitando a manutenção e evolução do código.

## DTO (Data Transfer Object)
Os DTOs são objetos simples usados para transferir dados entre diferentes camadas da aplicação. Eles são essenciais para evitar o acoplamento entre a camada de apresentação e a de negócios, além de melhorar a performance da aplicação ao transferir apenas os dados necessários.

Neste projeto, os DTOs foram utilizados para encapsular os dados trocados entre o frontend e o backend, garantindo uma comunicação eficiente e segura.

## Exception Handling
O tratamento de exceções é fundamental para garantir que a aplicação lide com erros de maneira controlada e informativa. Em nosso projeto, utilizamos o `ControllerAdvice` do Spring para gerenciar exceções de forma centralizada, fornecendo respostas apropriadas aos usuários e facilitando a identificação e correção de problemas.

## Swagger
A documentação é uma parte crucial de qualquer projeto, pois facilita a compreensão e o uso da API por outros desenvolvedores. Utilizamos o Swagger neste projeto para documentar todas as rotas e endpoints da aplicação, tornando a API mais acessível e fácil de usar.

![Swagger](https://swagger.io/swagger/media/assets/images/swagger_logo.svg)

## Docker
O Docker foi utilizado para garantir a consistência do ambiente de desenvolvimento, permitindo que a aplicação e seus serviços, como o MySQL e o Adminer, sejam executados de maneira isolada e replicável. Com o Docker, podemos facilmente configurar e executar a imagem do MySQL e gerenciar nosso banco de dados através do Adminer.

## Testes Automatizados
Testes automatizados são essenciais para garantir a qualidade e a confiabilidade do software. Eles permitem que identifiquemos problemas rapidamente e garantem que novas alterações não introduzam bugs. Neste projeto, implementei testes automatizados para validar o funcionamento correto das funcionalidades CRUD, garantindo a robustez da aplicação.

## Integração Contínua (CI)
A integração contínua é uma prática de desenvolvimento que permite a detecção rápida de problemas no código. Ela automatiza o processo de construção e teste da aplicação sempre que novas alterações são submetidas, garantindo que o código esteja sempre em um estado funcional. Neste projeto, configurei a CI para executar os testes automatizados e outras verificações, mantendo a qualidade do código e facilitando o desenvolvimento colaborativo.

Para executar este projeto, siga os seguintes passos:

### 1. Clonar o Repositório
Primeiro, você precisa clonar o repositório do GitHub para a sua máquina local. Execute o seguinte comando no terminal:

```bash
git clone https://github.com/Mauro-Pereira/-Learning-How-to-make-CRUD-using-Spring-Boot-with-Java-and-MySQL.git
````

### 2. Abrir o Projeto na IDE

Abra o projeto em uma IDE de sua preferência (por exemplo, IntelliJ IDEA, Eclipse, VSCode).

### 3. Subir o MySQL e o Adminer com Docker

Certifique-se de ter o Docker instalado na sua máquina. No terminal, navegue até o diretório raiz do projeto e execute o comando:

```bash
docker-compose up
````
Este comando irá iniciar os serviços MySQL e Adminer.

### 4. Executar o Projeto

Execute o projeto a partir da sua IDE.

### 5. Acessar o Swagger

Após executar o projeto, você pode acessar a documentação da API pelo Swagger através da seguinte URL:

http://localhost:8080/swagger-ui/index.html
