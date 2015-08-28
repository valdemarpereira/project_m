package com.valdemar.service;

import com.valdemar.dao.Activity;
import com.valdemar.dao.ActivityDao;
import com.valdemar.dao.Category;
import com.valdemar.dao.CategoryDao;
import com.valdemar.dao.Log;
import com.valdemar.dao.LogDao;
import com.valdemar.dao.exceptions.ActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by valdemar.pereira on 14/08/15.
 */
@Service
public class LogService {

    @Autowired
    LogDao logDao;

    @Autowired
    ActivityDao activityDao;

    public void log(String activityName, String notes) throws ActivityNotFoundException {

        Activity activity = activityDao.findByName(activityName);

        logDao.log(activity.getId(), new Date(), notes);
    }

}
