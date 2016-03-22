package ru.mipt.snp.logic.impl;

import ru.mipt.snp.logic.ProfileLogic;
import ru.mipt.snp.logic.SecurityLogic;
import ru.mipt.snp.domain.User;
import ru.mipt.snp.domain.UserDetailsImpl;
import ru.mipt.snp.dao.ProfileDao;
import org.springframework.security.context.SecurityContextHolder;

import java.util.List;
import java.util.Arrays;

/**
 * <p></p>
 *
 * @author Kirill Tsibriy
 * @since Feb 16, 2009 12:16:45 PM
 */
public class ProfileLogicImpl implements ProfileLogic {
    private ProfileDao profileDao;

    public User getUserByLogin(String login) {
        return profileDao.getUserByLogin(login);
    }

    @Override
    public User getUserById(int userId) {
        return profileDao.getUserById(userId);
    }

    public void updateUser(User user) {
        profileDao.updateUserProfile(user);
    }

    public List<User> getAllUsers() {
        return profileDao.getAllUsers();
    }

    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }
}
