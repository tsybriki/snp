package ru.mipt.snp.logic;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/08/2009  16:57
 */
public interface TrainingDataLogic {

    /**
     * Adds new training record for the user
     * @param trainingName userfriendly name for the training
     * @param trainingDescription some decription
     * @param trainingFileStream stream where to load training data from
     * @throws IOException exception
     */
    void addTrainingRecord(String trainingName, String trainingDescription,
                           InputStream trainingFileStream) throws IOException;
}
