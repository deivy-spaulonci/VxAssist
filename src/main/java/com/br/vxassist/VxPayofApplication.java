package com.br.vxassist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class VxPayofApplication {

    Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VxPayofApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            logger.info(" -> INICIANDO VxAssit... " + new Date());

            //throw new GenericErrorException("Código de Barras inválido");
            //                QTipoDespesa qTipoDespesa = QTipoDespesa.tipoDespesa;
//                JPAQuery<?> query = new JPAQuery<Void>(entityManager);
//                query.from(qTipoDespesa);
//                query.fetch().forEach(item ->{
//                    logger.info("{}", item);
//                });
        };
    }
}
