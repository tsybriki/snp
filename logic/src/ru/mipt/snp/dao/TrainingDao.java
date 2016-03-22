package ru.mipt.snp.dao;

import ru.mipt.snp.domain.Training;
import ru.mipt.snp.domain.TrainingData;
import ru.mipt.snp.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/06/2009  19:51
 */
public interface TrainingDao {

    /**
     * TODO: Do we need any restrictions on user trainings (overall number, dates) here?
     *
     * Retrieves all user trainings list
     * @param user TODO: replace by userId?
     * @return list of user trainings
     */
    List<Training> getUserTrainings(User user);

    /**
     * Retrieves user training binary data file for corresponding training object
     * @param training training to retrieve its data
     * @return training data for correspondent training
     */
    TrainingData getUserTrainingData(Training training);

    /**
     * Adds new user training record
     * TODO: should we also add training data in this method???
     * @param training training data object to store
     * @param dataStream data stream where to read training data from
     * @throws java.io.IOException exception
     */
    void addUserTraining(Training training, InputStream dataStream) throws IOException;
}
