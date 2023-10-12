# Cadastro de Pessoas Via CEP

Um projeto de cadastro de pessoas que utiliza o serviço ViaCEP para obter informações de endereço com base no CEP fornecido.

## Visão Geral

Este projeto é uma aplicação Spring Boot que fornece funcionalidades para criar, atualizar, recuperar e excluir registros de pessoas com informações de endereço obtidas automaticamente do serviço ViaCEP.

## Pré-requisitos

Antes de começar, certifique-se de ter atendido aos seguintes requisitos:

- Java Development Kit (JDK) instalado
- Maven instalado
- Um ambiente de desenvolvimento Java, como Eclipse, IntelliJ IDEA ou Visual Studio Code
- Conexão com a internet para acessar o serviço ViaCEP

## Configuração

### Configuração de Variáveis de Ambiente

Para rodar este projeto, você precisa configurar as seguintes variáveis de ambiente:

- `DATABASE_URL`: A URL do banco de dados. Por padrão, estamos usando o H2 Database, que está configurado para rodar em memória.
- `API_KEY`: Sua chave de API para acessar serviços externos (se aplicável).

### Configuração de Propriedades

Você também pode configurar propriedades no arquivo `application.properties`:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# Outras configurações
custom.property=algum-valor
