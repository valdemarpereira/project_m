package com.valdemar.service;

import com.valdemar.dao.Activity;
import com.valdemar.dao.ActivityDao;
import com.valdemar.dao.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by valdemar.pereira on 14/08/15.
 */
@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;


    public List<Activity> getAll(){
        return activityDao.findAll();
    }

}
