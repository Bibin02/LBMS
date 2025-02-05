package com.project.lbms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@Configuration
public class JpaConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        return emf;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public EntityTransaction transactionManager(EntityManager entityManager) {
        return entityManager.getTransaction();
    }
}
