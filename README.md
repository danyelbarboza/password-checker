# Validador de Senhas com JUnit 5 e Mockito

Este projeto é um estudo prático de testes unitários em Java, utilizando **JUnit 5** e **Mockito** para validar um conjunto robusto de regras de segurança de senha. O objetivo é demonstrar a criação de testes eficazes, cobrindo cenários de sucesso, falhas e exceções, além de isolar dependências externas.

## Tecnologias

* **Java 21**
* **Maven**
* **JUnit 5** (`api`, `engine`, `params`)
* **Mockito** (`core`)

## Regras de Validação de Senha

A classe `ValidadorDeSenha` aplica as seguintes regras:
* A senha não pode ser `null`.
* Deve ter no mínimo 8 caracteres.
* Deve conter pelo menos uma letra maiúscula.
* Deve conter pelo menos uma letra minúscula.
* Deve conter pelo menos um número.
* Deve conter pelo menos um caractere especial.
* Não pode ser uma senha comum (verificado através de um serviço externo simulado).

## Foco do Estudo em Testes

A suíte de testes `ValidadorDeSenhaTest` explora as seguintes funcionalidades:

* **`@Test`** e **`@DisplayName`**: Para criar e nomear testes de forma descritiva.
* **`@BeforeEach`**: Para inicializar mocks antes de cada teste.
* **`assertThrows`**: Para validar o lançamento de exceções, como `IllegalArgumentException` para senhas nulas e uma `ExceptionService` customizada para falhas no serviço.
* **`assertAll`**: Para agrupar múltiplas asserções e garantir que todas sejam verificadas, útil para testar senhas com múltiplos problemas.
* **`@ParameterizedTest`**, **`@CsvSource`** e **`@ValueSource`**: Para executar os mesmos testes com diferentes conjuntos de dados, otimizando a verificação de múltiplos cenários de senhas válidas e inválidas.
* **`@Mock`** e **`@InjectMocks`**: Para criar e injetar um mock do `MapService`, permitindo isolar a lógica de validação da dependência externa.
* **`Mockito.when()`**: Para simular o comportamento do `MapService`, incluindo o retorno de que uma senha é comum ou o lançamento de uma exceção.
* **`verify()`**: Para garantir que o método do serviço mockado foi (ou não foi) chamado, como no teste de senha nula.

## Como Executar os Testes

Para rodar os **19 testes** da suíte, use o comando Maven na raiz do projeto:

```bash
mvn test