package com.valdemar.dao;

import com.valdemar.dao.exceptions.ActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDaoImpl implements ActivityDao {

    private static final String SELECT_ALL_ACTIVITIES = "SELECT * FROM ACTIVITY";
    private static final String SELECT_ACTIVITY_BY_CATEGORY_ID = "SELECT * FROM ACTIVITY WHERE CATEGORY_ID = ?";
    private static final String SELECT_ACTIVITY_BY_NAME = "SELECT * FROM ACTIVITY WHERE NAME = ?";

    private static RowMapper<Activity> ACTIVITY_MAPPER = (resultSet, i) -> Activity.of(
            resultSet.getLong("ACTIVITY_ID"),
            resultSet.getString("NAME"),
            resultSet.getString("DESCRIPTION"),
            resultSet.getLong("CATEGORY_ID"),
            resultSet.getBoolean("ACTIVE"));

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Activity findByName(String name) throws ActivityNotFoundException {

        Activity activity = jdbcTemplate.queryForObject(
                SELECT_ACTIVITY_BY_NAME,
                new Object[]{name},
                ACTIVITY_MAPPER
        );

        if(activity == null){
            throw new ActivityNotFoundException(name);
        }

        return activity;
    }

    @Override
    public Activity findById(Long id) {
        throw new UnsupportedOperationException("TO IMPLEMENT");
    }

    @Override
    public Activity findByCategoryId(Long categoryId) {
        return jdbcTemplate.queryForObject(
                SELECT_ACTIVITY_BY_CATEGORY_ID,
                new Object[]{categoryId},
                ACTIVITY_MAPPER
        );
    }

    @Override
    public List<Activity> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL_ACTIVITIES,
                ACTIVITY_MAPPER);
    }
}
