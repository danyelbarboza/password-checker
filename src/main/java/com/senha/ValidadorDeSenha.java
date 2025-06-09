package com.senha;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeSenha {

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
