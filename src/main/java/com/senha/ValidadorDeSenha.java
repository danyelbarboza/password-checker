package com.senha;

import java.util.ArrayList;
import java.util.List;

import com.senha.service.ExceptionService;
import com.senha.service.MapService;

public class ValidadorDeSenha {
    private final MapService servico;

    public ValidadorDeSenha(MapService servico) {
        this.servico = servico;
    }

    public boolean cumprimentoMinimoValido(String senha) {
        return senha.length() >= 8;
    }

    public List<String> validar(String senha) {
        if (senha == null) {
            throw new IllegalArgumentException("Senha nao pode ser nula");
        } else {
            List<String> erros = new ArrayList<>();
            if (!cumprimentoMinimoValido(senha)) {
                erros.add("Senha deve ter pelo menos 8 caracteres");
            }
            if (!contemNumero(senha)) {
                erros.add("Senha deve conter pelo menos um numero");
            }
            if (!contemLetraMaiuscula(senha)) {
                erros.add("Senha deve conter pelo menos uma letra maiuscula");
            }
            if (!contemLetraMinuscula(senha)) {
                erros.add("Senha deve conter pelo menos uma letra minuscula");
            }
            if (!contemCaracterEspecial(senha)) {
                erros.add("Senha deve conter pelo menos um caracter especial");
            } try {
                if (servico.eSenhaComum(senha)) {
                    erros.add("A senha é muito comum, tente outra.");
                }
            } catch (Exception e) {
                throw new ExceptionService("Erro ao verificar senha comum");
            }
            return erros;
        }
    }

    public boolean contemNumero(String senha) {
        for (char item : senha.toCharArray()) {
            if (Character.isDigit(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean contemLetraMaiuscula(String senha) {
        for (char item : senha.toCharArray()) {
            if (Character.isUpperCase(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean contemLetraMinuscula(String senha) {
        for (char item : senha.toCharArray()) {
            if (Character.isLowerCase(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean contemCaracterEspecial(String senha) {
        for (char item : senha.toCharArray()) {
            if (!Character.isLetterOrDigit(item)) {
                return true;
            }
        }
        return false;
    }

}
