package com.valdemar.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryDao {

    Category findByName(String name);
    Category findById(Long id);
    List<Category> findAll();
}
