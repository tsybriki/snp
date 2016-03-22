package ru.mipt.snp.logic.impl;

import ru.mipt.snp.dao.TrainingDao;
import ru.mipt.snp.dao.ProfileDao;
import ru.mipt.snp.domain.Training;
import ru.mipt.snp.logic.SecurityLogic;
import ru.mipt.snp.logic.TrainingDataLogic;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/08/2009  17:00
 */
public class TrainingDataLogicImpl implements TrainingDataLogic{

    private SecurityLogic securityLogic;
    private TrainingDao trainingDao;
    private ProfileDao profileDao;  

    @Override
    public void addTrainingRecord(String trainingName, String trainingDescription,
                                  InputStream trainingFileStream) throws IOException {

        Training training = new Training();
        training.setName(trainingName);
        training.setUserId(securityLogic.getUserDetailsFromContext().getUser().getId());
        training.setDescription(trainingDescription);

        trainingDao.addUserTraining(training, trainingFileStream);
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public void setSecurityLogic(SecurityLogic securityLogic) {
        this.securityLogic = securityLogic;        
    }

    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }
}
