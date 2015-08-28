package com.valdemar.dao;

import com.valdemar.dao.exceptions.ActivityNotFoundException;

import java.util.List;

public interface ActivityDao {

    Activity findByName(String name) throws ActivityNotFoundException;
    Activity findById(Long id);
    Activity findByCategoryId(Long id);
    List<Activity> findAll();
}
