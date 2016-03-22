package ru.mipt.snp.dao.jdbc;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import ru.mipt.snp.dao.TrainingDao;
import ru.mipt.snp.dao.jdbc.common.QueryHolder;
import ru.mipt.snp.dao.jdbc.common.Query;
import ru.mipt.snp.dao.jdbc.mapping.TrainingCallbackHandler;
import ru.mipt.snp.domain.Training;
import ru.mipt.snp.domain.TrainingData;
import ru.mipt.snp.domain.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/06/2009  19:54
 */
public class JdbcTrainingDao implements TrainingDao{

    protected static final Logger log = Logger.getLogger(JdbcTrainingDao.class);

    // 1Mb
    private static final int MAX_BLOB_FILE_SIZE = 1024*1024;

    private NamedParameterJdbcTemplate template;
    private QueryHolder queries;

    public JdbcTrainingDao(DataSource ds) {
        this.template = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public List<Training> getUserTrainings(User user) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Requested trainings for user with login_id = %1$s", user.getLogin()));
        }

        final List<Training> trainings = new ArrayList<Training>();
        final Query query = queries.create("get.user.trainings");

        template.query(query.toSql(),
                       Collections.singletonMap("userid", user.getId()),
                       new RowCallbackHandler(){
                           @Override
                           public void processRow(ResultSet rs) throws SQLException {
                               Training training = new Training();
                               training.setId(rs.getInt("id"));
                               training.setUserId(rs.getInt("user_id"));
                               training.setName(rs.getString("name"));
                               training.setDescription(rs.getString("description"));
                               
                               trainings.add(training);
                               if (log.isDebugEnabled()) {
                                    log.debug(String.format("Loaded training %1$s", training));
                                }
                           }
                       });            
        return trainings;
    }

    @Override
    public TrainingData getUserTrainingData(Training training) {
        return null;
    }

    @Override
    public void addUserTraining(Training training, final InputStream dataStream) throws IOException{
        MapSqlParameterSource trainingMap = new MapSqlParameterSource();
        trainingMap.addValue("user_id", training.getUserId(), Types.INTEGER);
        trainingMap.addValue("name", training.getName(), Types.VARCHAR);
        trainingMap.addValue("description", training.getDescription(), Types.VARCHAR);
        final GeneratedKeyHolder trainingKeyHolder = new GeneratedKeyHolder();
        template.update(queries.create("insert.new.user.training").toSql(), trainingMap, trainingKeyHolder);

        // save training data
        template.getJdbcOperations().execute(queries.create("insert.user.training.data").toSql(),
                new AbstractLobCreatingPreparedStatementCallback(new DefaultLobHandler()) {
                    @Override
                    protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                        preparedStatement.setInt(1, trainingKeyHolder.getKey().intValue());
                        lobCreator.setBlobAsBinaryStream(preparedStatement, 2, dataStream, MAX_BLOB_FILE_SIZE);
                    }
                });

    }

    public void setQueries(QueryHolder queries) {
        this.queries = queries;
    }
    
}
