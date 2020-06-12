package com.br.vxassist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories("com.br.vxassist.repository")
@EntityScan("com.br.vxassist.model")
@ComponentScan("com.br.vxassist")
public class VxPayofApplication {

    private static final Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);

//    @PersistenceContext
//    private EntityManager entityManager;


    public static void main(String[] args) {

        SpringApplication.run(VxPayofApplication.class, args);

    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            logger.info(" -> INICIANDO VxAssit... 3" + new Date());

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
