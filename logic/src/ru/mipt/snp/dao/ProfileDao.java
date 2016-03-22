package ru.mipt.snp.dao;

import org.springframework.security.userdetails.UserDetailsService;
import ru.mipt.snp.domain.User;

import java.util.List;

/**
 * <p>Interface for accessing various profile data</p>
 *
 * Created by Kirill Tsibriy on 01.03.2009
 */
public interface ProfileDao extends UserDetailsService {

    /**
     * Updates user profile in database
     * @param user User domain object to update profile in database
     */
    void updateUserProfile(User user);

    /**
     * Returns user for specified login
     * @param login String
     * @return User
     */
    User getUserByLogin(String login);

    /**
     *
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * Returns all users from the storage
     * @return list of Users
     */
    List<User> getAllUsers();
}
