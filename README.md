## Lojinha API Automção
Esse é um repositório que contém a automação de alguns testes de API Rest de um software denominado Lojinha. Os subtópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.

## Técnologias Utilizadas

- Java
  https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- JUnit
  https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.7.1
- RestAssured
  https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.4.0
- Maven
  https://maven.apache.org/

## Testes Automatizados
Testes para validar as partições de equivalência relacionadas ao valor do produto na Lojinha, que estão vinculados diretamente a regra de negócio que diz que o valor do produto de estar entre R$ 0,01 e R$ 7.000,00

## Notas Gerais

- Sempre utilizamos a anotação Before Each para capturar o token que
  será utilizado posteriormente nos métodos de teste.

- Armazenamos todos os dados que são enviados para a API através do uso de classes POJO.
- Criamo dados iniciais através do uso de classes Data Factory, para facilitar a criação e controle dos mesmos.
- Nesse projeto fazemos o uso do JUnit 5, o que dá a possibilidade de usar a anotação DisplayName para dar descrições em português para nossos testes.
   
