package com.project.lbms.service;

import jakarta.persistence.EntityManager;

public class ProjectDataManager {

    private ProjectBeanFactory pbf = new ProjectBeanFactory();
    
    
    public EntityManager getProjectEntityManager() {
        return pbf.getBean("EntityManager");
    }

}
