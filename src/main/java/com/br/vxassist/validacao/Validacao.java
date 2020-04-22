package com.br.vxassist.validacao;

public abstract class Validacao {

    public static boolean isSomenteNumero(String valor){
        return valor.matches("^[0-9]*$");
    }
}
