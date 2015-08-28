package com.valdemar.dao;

import java.util.Date;
import java.util.List;

public interface LogDao {

    void log(Long activityId, Date date, String notes);
    List<Log> last(int days);
    List<Log> findByActivityId(Long id);
}
