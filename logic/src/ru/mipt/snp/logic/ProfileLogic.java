package ru.mipt.snp.logic;

import ru.mipt.snp.domain.User;

import java.util.List;

/**
 * <p>Logic interface for interacting with user profiles</p>
 *
 * @author Kirill Tsibriy
 * @since Feb 16, 2009 12:16:12 PM
 */
public interface ProfileLogic {

    /**
     * Returns user for provided login
     * @param login String
     * @return user for provided login
     */
    User getUserByLogin(String login);

    /**
     *
     * @param userId
     * @return
     */
    User getUserById(int userId);
    
    /**
     * Updates provided user detailes in datastore
     * @param user User to update
     */
    void updateUser(User user);

    /**
     * Returns all users in the system sorted by name
     * @return list of users
     */
    List<User> getAllUsers();
}
