package ru.mipt.snp.dao.jdbc;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import ru.mipt.snp.dao.ProfileDao;
import ru.mipt.snp.dao.jdbc.mapping.UserMapping;
import ru.mipt.snp.dao.jdbc.mapping.UserCallbackHandler;
import ru.mipt.snp.dao.jdbc.mapping.UserDetailsCallbackHandler;
import ru.mipt.snp.dao.jdbc.common.QueryHolder;
import ru.mipt.snp.dao.jdbc.common.Query;
import ru.mipt.snp.domain.User;
import ru.mipt.snp.domain.UserDetailsImpl;
import ru.mipt.snp.domain.GenderEnum;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * <p>TODO: Write javadoc</p>
 *
 * Created by Kirill Tsibriy on 01.03.2009
 */
public class JdbcProfileDao implements ProfileDao {

    private static final SimpleDateFormat MYSQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    private final NamedParameterJdbcTemplate template;
    private static final Logger log = Logger.getLogger(JdbcProfileDao.class.getName());
    private QueryHolder queries;

    public JdbcProfileDao(DataSource ds) {
        this.template = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Requested user with login_id = %1$s", s));
        }

        final User user = new User();
        final UserDetailsImpl udi = new UserDetailsImpl(user);
        final Query query = queries.create("get.user.with.roles");
        
        template.query(query.toSql(), 
                       Collections.singletonMap("userid", s),
                       new UserDetailsCallbackHandler(udi));
        return udi;
    }

    @Override
    public User getUserByLogin(String login) {
        return ((UserDetailsImpl) loadUserByUsername(login)).getUser();
    }

    @Override
    public User getUserById(int userId) {
        final User user = new User();
        final Query query = queries.create("get.user.by.id");

        template.query(query.toSql(),
                      Collections.singletonMap("userId", userId),
                      new UserCallbackHandler(user));
        
        if(user.getId() == 0){
            return null;
        }
        return user;
    }

    @Override
    public void updateUserProfile(final User user) {
        final Query query = queries.create("update.user.profile");
        Map<String, String> m = new HashMap<String, String>(4);
        m.put("firstName", user.getFirstName());
        m.put("lastName", user.getLastName());
        m.put("userid", user.getLogin());
        m.put("password", user.getPassword());
        Date birthDate = user.getBirthDate();
        m.put("birthDate", birthDate!=null ? MYSQL_DATE_FORMAT.format(birthDate) : null); 
        m.put("gender", user.getGender().getGenderString());
        template.update(query.toSql(), m);
    }

    @Override
    public List<User> getAllUsers() {
        final Query query = queries.create("get.all.users");
        final List<User> users = new LinkedList<User>();
        template.query(query.toSql(), Collections.EMPTY_MAP, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                final User user = new User();
                user.setId(resultSet.getInt(UserMapping.ID));
                user.setFirstName(resultSet.getString(UserMapping.FIRST_NAME));
                user.setLastName(resultSet.getString(UserMapping.LAST_NAME));
                user.setGender(GenderEnum.getGenderByString(resultSet.getString(UserMapping.GENDER)));
                user.setBirthDate(resultSet.getDate(UserMapping.BIRTH_DATE));
                users.add(user);
                if (log.isDebugEnabled()) {
                    log.debug(String.format("Loaded user %1$s", user));
                }

            }
        });
        return users;
    }

    private static User user(String firstName, String lastName) {
        final User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
    
    public void setQueries(QueryHolder queries) {
        this.queries = queries;
    }
}
