package com.project.lbms.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
@EnableJpaRepositories(basePackages = "com.project.lbms.repository")
public class JpaConfig {

    @Bean
    public DataSource dataSource() {
        // Load properties from persistence.xml
        Map<String, Object> jpaProperties = Persistence.createEntityManagerFactory("pu").getProperties();

        return DataSourceBuilder.create()
                .driverClassName((String) jpaProperties.get("javax.persistence.jdbc.driver"))
                .url((String) jpaProperties.get("javax.persistence.jdbc.url"))
                .username((String) jpaProperties.get("javax.persistence.jdbc.user"))
                .password((String) jpaProperties.get("javax.persistence.jdbc.password"))
                .build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        return emf;
    }

    // @Bean
    // public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
    //     return entityManagerFactory.createEntityManager();
    // }

    // @Bean
    // public EntityTransaction entityTransaction(EntityManager entityManager) {
    //     return entityManager.getTransaction();
    // }
}
