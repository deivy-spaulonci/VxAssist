package com.br.vxassist.exception;


import com.br.vxassist.VxPayofApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericErrorException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);

    public GenericErrorException(){}

    public GenericErrorException(String message){
        super(message);
        logger.error(message);
    }
}
