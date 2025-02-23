package com.project.lbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lbms.config.ProjectBeanFactory;

import jakarta.persistence.EntityManager;

@Service
public class ProjectDataManager {
    
    @Autowired
    private ProjectBeanFactory pbf;
    
    
    public EntityManager getProjectEntityManager() {
        return pbf.getProjectBean(EntityManager.class);
    }

}
