# Validador de Senhas com JUnit 5

Este projeto é um estudo prático de testes unitários com **JUnit 5** para validar um conjunto de regras de segurança de senha em Java.


## Tecnologias

  * **Java 21**
  * **Maven**
  * **JUnit 5** (`api`, `engine`, `params`)


## Foco do Estudo em JUnit 5

A suíte de testes `ValidadorDeSenhaTest` explora as seguintes funcionalidades:

  * **`@Test`** e **`@DisplayName`**: Para criar e nomear testes descritivos.
  * **`@BeforeAll`**: Para inicialização de recursos compartilhados antes dos testes.
  * **`assertThrows`**: Para validar o lançamento de exceções.
  * **`assertAll`**: Para agrupar múltiplas asserções e garantir que todas sejam verificadas.
  * **`@ParameterizedTest`** e **`@CsvSource`**: Para executar o mesmo teste com diferentes conjuntos de dados, otimizando a verificação de múltiplos cenários.


## Como Executar os Testes

Para rodar os 17 testes da suíte, use o comando Maven na raiz do projeto:

```bash
mvn test
```