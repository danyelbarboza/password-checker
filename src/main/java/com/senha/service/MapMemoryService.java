package com.senha.service;

import java.util.HashSet;
import java.util.Set;

public class MapMemoryService implements MapService {
    
    private final Set<String> SENHAS_COMUNS;
    
    public MapMemoryService() {
        SENHAS_COMUNS = new HashSet<>();
        SENHAS_COMUNS.add("12345678");
        SENHAS_COMUNS.add("123456789");
        SENHAS_COMUNS.add("1234567890");
        SENHAS_COMUNS.add("abc123");
    }

    public void addSenha(String senha) {
        SENHAS_COMUNS.add(senha);
    }

    public void removeSenha(String senha) {
        SENHAS_COMUNS.remove(senha);
    }

    public Set<String> getMap() {
        return SENHAS_COMUNS;
    }

    public void clear() {
        SENHAS_COMUNS.clear();
    }

    @Override
    public boolean eSenhaComum(String senha) {
        return SENHAS_COMUNS.contains(senha.toLowerCase());
    }
}
