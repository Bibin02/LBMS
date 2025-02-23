package com.project.lbms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Users;
import com.project.lbms.service.ProjectDataManager;

@Repository
public class SampleRepo {

    @Autowired
    ProjectDataManager pdm;

    public Users getUsers(){
        return pdm.getProjectEntityManager().find(Users.class,101);
    }
}
