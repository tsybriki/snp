package ru.mipt.snp.dao.jdbc.mapping;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.security.userdetails.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

import ru.mipt.snp.domain.User;
import ru.mipt.snp.domain.UserDetailsImpl;
import ru.mipt.snp.domain.GenderEnum;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 22.11.2009
 */
public class UserDetailsCallbackHandler implements RowCallbackHandler {

    private UserDetailsImpl userDetails;

    public UserDetailsCallbackHandler(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void processRow(ResultSet resultSet) throws SQLException {
        final User user = userDetails.getUser();
        UserCallbackHandler userCallbackHandler = new UserCallbackHandler(user);
        userCallbackHandler.processRow(resultSet);

        userDetails.addAuthority(resultSet.getString(UserMapping.ROLE_ID));
    }

    public UserDetailsImpl getUserDetails() {
        return userDetails;
    }
}
