package com.br.vxassist.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postEntityManagerFactory",
        transactionManagerRef = "postTransactionManager",
        basePackages = {"com.br.vxassist.postgres.repository"}
)
public class DataSourcePOSTConfig {
    @Bean(name = "dataSourcePOST")
    @ConfigurationProperties(prefix = "spring.post")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourcePOST") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.br.vxassist.postgres.model")
                .persistenceUnit("puPOST")
                .build();
    }

    @Bean(name = "postTransactionManager")
    public PlatformTransactionManager postTransactionManager(
            @Qualifier("postEntityManagerFactory") EntityManagerFactory postTransactionManager) {
        return new JpaTransactionManager(postTransactionManager);
    }


}
