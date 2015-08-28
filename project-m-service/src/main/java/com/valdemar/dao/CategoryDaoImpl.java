package com.valdemar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORY";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Category findByName(String name) {
        throw new UnsupportedOperationException("TO IMPLEMENT");
    }

    @Override
    public Category findById(Long id) {
        throw new UnsupportedOperationException("TO IMPLEMENT");
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL_CATEGORIES,
                (resultSet, rowNum) ->
                        new Category(
                                resultSet.getLong("CATEGORY_ID"),
                                resultSet.getString("CATEGORY"),
                                resultSet.getString("DESCRIPTION"),
                                resultSet.getBoolean("ACTIVE")));

    }

}
