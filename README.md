# DummyJSON Client - Java 21 e Spring Boot 3.8.5

## Descrição do Projeto

Este projeto é um microsserviço Java que interage com a API pública [DummyJSON](https://dummyjson.com/docs/products)
para realizar operações de busca de produtos. O projeto foi desenvolvido usando Java 8 e Spring Boot 2.6.x.

O Projeto conforme objetivo fornecido foi migrado para Java 21 e Spring Boot 3.8.5.

## Objetivo do Desafio

O desafio consiste em migrar este projeto para Java 17 e Spring Boot 3.2.5. Durante a migração, você enfrentará várias
dificuldades, incluindo a adaptação ao novo namespace, substituição de métodos depreciados e ajustes em testes
unitários.

## Funcionalidades

- **Consulta de Produtos**: Realiza chamadas para a API do DummyJSON para buscar informações sobre produtos.
- **Integração com `RestTemplate`**: Utiliza `RestTemplate` para realizar chamadas HTTP.
- **Validação de Dados**: Validação de dados de entrada usando Bean Validation (`javax.validation`).
- **Gestão de Dependências**: Configurado para utilizar @Autoweird.
- **Testes Unitários**: Inclui testes unitários desenvolvidos com JUnit 4 e Mockito.

## Estrutura do Projeto

```bash
dummyjson-client
├── pom.xml
└── src
    ├── main
    │ ├── java
    │ │   └── com
    │ │     └── example
    │ │         └── dummyjson
    │ │             ├── config
    │ │             ├── FeignProduct.java
    │ │             │ ├── RestTemplateConfig.java
    │ │             │ ├── SwaggerConfig.java
    │ │             │ └── WebClientConfig.java
    │ │             ├── controller
    │ │             │ ├── ProductController.java
    │ │             │ ├── ProductFeignController.java
    │ │             │ └── ProductWebClientController.java
    │ │             ├── dto
    │ │             │ ├── Product.java
    │ │             │ └── ProductResponse.java
    │ │             ├── DummyJsonClientApplication.java
    │ │             └── service
    │ │                 ├── ProductFeignService.java
    │ │                 ├── ProductService.java
    │ │                 └── ProductWebClientService.java
    │ └── resources
    │     └── application.yaml
    └── test
        └── java
            └── com
                └── example
                    └── dummyjson
                        ├── config
                        │ ├── RestTemplateConfigTest.java
                        │ └── WebClientConfigTest.java
                        ├── controller
                        │ ├── ProductControllerTest.java
                        │ ├── ProductFeignControllerTest.java
                        │ └── ProductWebClientControllerTest.java
                        ├── dto
                        │ └── ProductTest.java
                        └── service
                            ├── ProductFeignServiceTest.java
                            ├── ProductServiceTest.java
                            └── ProductWebClientServiceTest.java

```

## Passos para Executar o Projeto

### Pré-requisitos

- **Java 21**
- **Maven 3.8.x**

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.com/Felipe-Abreu/code-challenge-migration
    cd dummyjson-client
    ```

2. Compile e execute o projeto:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Acesse o serviço:

   O serviço estará disponível em `http://localhost:8080`.

### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```

## Requisitos de Entrega

1. Atualizar o `pom.xml` para usar Java 17+ e Spring Boot 3.2.5.
2. Substituir `RestTemplate` por `WebClient` ou `Openfeign`.
3. Substituir os testes unitários feitos com `JUnit 4` e `Mockito` por testes de integração utilizando
   `@SpringBootTest`.
4. Refatorar qualquer código depreciado ou incompatível.
5. Garantir que todos os testes ainda passam após a migração.
6. Deixar a URL da API dummyjson parametrizada por ambiente no projeto.
7. Adicionar no projeto um novo path `/health` que retorna a saude do microsserviço.

## Validação Sobre o Challenge

- O projeto deve estar funcionando em Java 17 e Spring Boot 3.2.5.
- Todos os testes unitários devem ser executados e passar sem falhas.
- O código deve estar devidamente documentado e organizado.

## Extras

- Entregar o projeto em container será um diferencial.
- Fica a critério do desenvolvedor inserir ou remover dependencias do projeto para garantir o objetivo do challenge.

## O que foi feito

- Foi realizado a migração para versão 21 do Java.
- Migração do Spring para 3.8.5.
- Atualização das dependencias existentes e adicionado as dependencias para FeignClient, WebClient e Lombok.
- No projeto foi aproveitado que utilizava uma versão compativel com Java 8 do RestTemplate, assim optando somente por atualizar a versão da depêndencia e utilizar o memso.
- No projeto foi implementado a possibilidade de se realizar consultas tanto com RestTemplate, quanto com FeignClient e WebClient.
   - Sendo assim os endpoints ficaram:
     - FeignClient: localhost:8080/feign/api/products/{id} e localhost:8080/feign/api/products
     - RestTemplate: localhost:8080/rest/api/products/{id} e localhost:8080/rest/api/products
     - WebClient: localhost:8080/web/api/products/{id} e localhost:8080/web/api/products
- Também foi criado o DockerFile do projeto e seu respectivo docker-compose