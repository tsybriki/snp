package ru.mipt.snp.web.userdetails;

import ru.mipt.snp.web.common.WebConstants;
import ru.mipt.snp.logic.TrainingDataLogic;
import ru.mipt.snp.logic.ProfileLogic;
import ru.mipt.snp.logic.SecurityLogic;
import ru.mipt.snp.dao.TrainingDao;
import ru.mipt.snp.domain.User;
import ru.mipt.snp.domain.Training;
import ru.mipt.snp.domain.UserDetailsImpl;

import java.util.List;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 29.12.2009 
 */
public class UserTrainingsAction {

    private TrainingDao trainingDao;
    private TrainingDataLogic logic;
    private ProfileLogic profileLogic;
    private SecurityLogic securityLogic;

    private List<Training> trainings;

    private int userId;

    public String list() {
        User user;
        // TODO: move this to some common class???
        if(userId == 0){
            UserDetailsImpl details = securityLogic.getUserDetailsFromContext();
            user = details.getUser();
        }
        else{
            user = profileLogic.getUserById(userId);
        }
        trainings = trainingDao.getUserTrainings(user);
        return WebConstants.SUCCESS;
    }

    public String add() {
        return WebConstants.SUCCESS;
    }

    public String execute() {
        return WebConstants.SUCCESS;        
    }

    public int getUserId() {
        return userId;
    }

    // TODO: fix this mess up with id/userId
    public void setId(int userId) {
        this.userId = userId;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setLogic(TrainingDataLogic logic) {
        this.logic = logic;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public void setProfileLogic(ProfileLogic profileLogic) {
        this.profileLogic = profileLogic;
    }

    public void setSecurityLogic(SecurityLogic securityLogic) {
        this.securityLogic = securityLogic;
    }
}
