package com.project.lbms.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@Service
public class ProjectBeanFactory implements ApplicationContextAware{
    
    private static ApplicationContext context;

    public EntityManager getBean(String className) {
        return context.getBean(EntityManager.class);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
