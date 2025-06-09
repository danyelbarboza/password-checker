package com.senha;

public class ControleDeAcesso {
    
    public boolean podeEntrar(boolean usuarioAtivo, boolean temCredencial) {
        return usuarioAtivo && temCredencial;
    }
}
