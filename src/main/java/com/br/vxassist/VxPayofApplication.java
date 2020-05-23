package com.br.vxassist;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.br.vxassist.repository")
public class VxPayofApplication {

    private static final Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);

//    @PersistenceContext
//    private EntityManager entityManager;


    public static void main(String[] args) {
        //CORRE MODO FX
        Application.launch(AppFx.class, args);
        //CORRE O SPRING NOMAL
        //SpringApplication.run(VxPayofApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            logger.info(" -> INICIANDO VxAssit...");

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
