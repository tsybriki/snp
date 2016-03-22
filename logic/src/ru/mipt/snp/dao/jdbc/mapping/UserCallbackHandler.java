package ru.mipt.snp.dao.jdbc.mapping;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import ru.mipt.snp.domain.User;
import ru.mipt.snp.domain.GenderEnum;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 22.11.2009
 */
public class UserCallbackHandler implements RowCallbackHandler {

    private User user;

    public UserCallbackHandler(User user){
        this.user = user;        
    }

    @Override
    public void processRow(ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(UserMapping.ID));
        user.setLogin(resultSet.getString(UserMapping.LOGIN_ID));
        user.setFirstName(resultSet.getString(UserMapping.FIRST_NAME));
        user.setLastName(resultSet.getString(UserMapping.LAST_NAME));
        user.setPassword(resultSet.getString(UserMapping.PASSWORD));
        user.setBirthDate(resultSet.getDate(UserMapping.BIRTH_DATE));
        user.setGender(GenderEnum.getGenderByString(
                            resultSet.getString(UserMapping.GENDER)));
    }

    public User getUser() {
        return user;
    }
}
