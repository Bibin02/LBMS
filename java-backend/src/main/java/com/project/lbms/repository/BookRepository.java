package com.project.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{

}
