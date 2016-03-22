package ru.mipt.snp.web.userdetails;

import ru.mipt.snp.web.common.WebConstants;
import ru.mipt.snp.web.forms.UserPreferencesForm;
import ru.mipt.snp.logic.ProfileLogic;
import ru.mipt.snp.domain.User;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>Action for listing users</p>
 *
 * @author Kirill Tsibriy
 * @since 08.11.2009
 */
public class ListUsersAction {
    private ProfileLogic logic;
    private List<UserPreferencesForm> users;

    public String execute() {
        List<User> domainUsersList = logic.getAllUsers();
        users = new ArrayList<UserPreferencesForm>(domainUsersList.size());
        for(User user:  domainUsersList){
            users.add(new UserPreferencesForm(user));
        }
        return WebConstants.SUCCESS;
    }

    public void setLogic(ProfileLogic logic) {
        this.logic = logic;
    }

    public List<UserPreferencesForm> getUsers() {
        return users;
    }
}
