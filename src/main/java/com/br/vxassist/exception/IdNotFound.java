package com.br.vxassist.exception;

import javax.persistence.EntityNotFoundException;

public class IdNotFound extends EntityNotFoundException {
    public IdNotFound(){
        super("Id para a operação não encontrado!!");
    }
}
