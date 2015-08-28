package com.valdemar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class LogDaoImpl implements LogDao {

    private static final String ADD_ACTIVITY_LOG = "INSERT INTO LOG(ACTIVITY_ID, DATE, NOTES) VALUES(?,?,?)";
    private static final String SELECT_LOG_LAST_N_DAYS = "SELECT * FROM LOG WHERE DATE >= ( CURDATE() - INTERVAL ? DAY )";
    private static final String SELECT_ALL_ACTIVITIES = "SELECT * FROM LOG WHERE ACTIVITY_ID = ?";
    private static final RowMapper<Log> LOG_ROW_MAPPER = (resultSet, rowNr) ->  Log.of(
            resultSet.getLong("LOG_ID"),
            resultSet.getLong("ACTIVITY_ID"),
            resultSet.getDate("DATE"),
            resultSet.getString("NOTES"));

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void log(Long activityId, Date date, String notes) {
        int rows = jdbcTemplate.update(
                ADD_ACTIVITY_LOG,
                new Object[] {
                        activityId,
                        date,
                        notes
                }
        );

        if(rows == 0){
            //TODO: Handle error
        }
    }

    @Override
    public List<Log> last(int days) {
        return jdbcTemplate.query(
                SELECT_LOG_LAST_N_DAYS,
                new Object[]{days},
                LOG_ROW_MAPPER

        );
    }

    @Override
    public List<Log> findByActivityId(Long activityId) {

        return jdbcTemplate.query(
                SELECT_ALL_ACTIVITIES,
                new Object[]{activityId},
                LOG_ROW_MAPPER

        );
    }
}
