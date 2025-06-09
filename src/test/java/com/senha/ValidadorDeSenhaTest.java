package com.senha;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import com.senha.service.ExceptionService;
import com.senha.service.MapService;

public class ValidadorDeSenhaTest {

    @InjectMocks
    private ValidadorDeSenha validadorDeSenha;
    @Mock
    private MapService mapService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
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
        verify(mapService, never()).eSenhaComum(senha);
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

    @DisplayName("Validacao de senha valida")
    @ParameterizedTest
    @ValueSource(strings = {
        "1234D!5a67",
        "1234D!5a678"
    })
    public void deveRetornarListaVaziaParaSenhasValidas(String senha) {
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(erros.isEmpty());
        verify(mapService).eSenhaComum(senha);
    }

    // Mockito
    @Test
    @DisplayName("Validacao de senha que esta no map")
    public void deveRejeitarSenhaQueEstaNoMap() {
        String senha = "password123";
        Mockito.when(mapService.eSenhaComum(senha)).thenReturn(true);
        List<String> erros = validadorDeSenha.validar(senha);
        assertTrue(erros.contains("A senha é muito comum, tente outra."));
    }

    @Test
    @DisplayName("Deve lançar ExceptionService se o serviço externo falhar")
    public void deveLancarExcecaoQuandoServicoFalhar() {
        String senha = "password123";
        Mockito.when(mapService.eSenhaComum(senha)).thenThrow(new RuntimeException("Erro de comunicação com API"));
        assertThrows(ExceptionService.class, () -> validadorDeSenha.validar(senha));
    }
}
