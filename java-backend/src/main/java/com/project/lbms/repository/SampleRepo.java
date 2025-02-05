package com.project.lbms.repository;

import org.springframework.stereotype.Repository;

import com.project.lbms.model.Users;
import com.project.lbms.service.ProjectDataManager;

import jakarta.persistence.EntityManager;

@Repository
public class SampleRepo {
    public Users getUsers(){

        ProjectDataManager pdm = new ProjectDataManager();
        EntityManager em = pdm.getProjectEntityManager();

        return em.find(Users.class,101);
    }
}
