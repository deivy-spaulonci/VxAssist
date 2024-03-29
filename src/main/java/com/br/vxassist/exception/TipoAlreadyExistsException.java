package com.br.vxassist.exception;

import javax.persistence.EntityExistsException;

public class TipoAlreadyExistsException extends EntityExistsException {
    public TipoAlreadyExistsException(String nome) {
        super(String.format("Tipo com esse id/nome: %s, já existe", nome));
    }
}
