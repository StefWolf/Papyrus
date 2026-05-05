package com.stefane.article_manager.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;


import java.util.HashMap;
import java.util.Map;


import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.stefane.article_manager.repository",
        entityManagerFactoryRef = "domainEntityManagerFactory",
        transactionManagerRef = "domainTransactionManager"
)
public class DomainDatabaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.domain")
    public DataSourceProperties domainDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource domainDataSource() {
        return domainDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean domainEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);

        return builder
                .dataSource(domainDataSource())
                .packages("com.stefane.article_manager.model")
                .persistenceUnit("domain")
                .properties(properties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager domainTransactionManager(
            @Qualifier("domainEntityManagerFactory") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}