package com.senha;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidadorDeSenhaTest {

    private static ValidadorDeSenha validadorDeSenha;
    

    @BeforeAll
    public static void setup() {
        validadorDeSenha = new ValidadorDeSenha();
    }

    @Test
    @DisplayName("Validacao de senha com exatamente oito caracteres")
    public void exatamenteOitoCaracteres() {
        String senha = "1234D!5a67";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha com mais de oito caracteres")
    public void maisDeOitoCaracteres() {
        String senha = "1234D!5a678";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha com menos de oito caracteres")
    public void menosDeOitoCaracteres() {
        String senha = "1D!45a6";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(!erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha nula")
    public void senhaNula() {
        String senha = null;
        assertThrows(IllegalArgumentException.class, () -> validadorDeSenha.validar(senha));
    }

    @Test
    @DisplayName("Validacao de senha sem numero")
    public void senhaSemNumero() {
        String senha = "D!5a67";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(!erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha sem letra maiuscula")
    public void senhaSemLetraMaiuscula() {
        String senha = "123!5a67";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(!erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha sem letra minuscula")
    public void senhaSemLetraMinuscula() {
        String senha = "123D!567";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(!erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha sem caracter especial")
    public void senhaSemCaracterEspecial() {
        String senha = "123D5a67";
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(!erros.isEmpty());
    }

    @Test
    @DisplayName("Validacao de senha com multiplos erros")
    public void multiplosErros() {
        String senha = "senha";
        List<String> erros = validadorDeSenha.validar(senha);
        assertAll("Validacao de senha com multiplos erros:",
            () -> assertTrue(!erros.isEmpty()),
            () -> assertEquals(4, erros.size()),
            () -> assertTrue(erros.contains("Senha deve ter pelo menos 8 caracteres")),
            () -> assertTrue(erros.contains("Senha deve conter pelo menos um numero")),
            () -> assertTrue(erros.contains("Senha deve conter pelo menos uma letra maiuscula")),
            () -> assertTrue(erros.contains("Senha deve conter pelo menos um caracter especial"))
        );
    }

    @ParameterizedTest
    @CsvSource({
        "1234D!5a67, true",
        "1234D!5a678, true",
        "1D!45a6, false",
        "D!5a67, false",
        "123!5a67, false",
        "123D!567, false",
        "123D5a67, false",
        "senha, false"
    })
    @DisplayName("Validacao de senha com parametros")
    public void validarSenha(String senha, boolean resultadoEsperado) {
        List<String> erros = validadorDeSenha.validar(senha);
        assertEquals(resultadoEsperado, erros.isEmpty());
    }
}
