package com.br.vxassist;


import com.br.vxassist.model.NodeFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("com.br.vxassist.repository")
@EntityScan("com.br.vxassist.model")
@ComponentScan("com.br.vxassist")
public class VxPayofApplication {

    //private static final Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);
    Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);
//    @PersistenceContext
//    private EntityManager entityManager;

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
