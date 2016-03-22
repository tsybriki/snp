package ru.mipt.snp.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.mipt.snp.domain.Training;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/08/2009  15:32
 */
public class JdbcTrainingDaoTest extends JdbcDaoTest{

    @Test
    public void stringTestsInitiation_success(){
        Assert.assertNotNull(trainingDao);
    }

    @Test
    public void addTraining_nullDataStream_success() throws IOException {
        Training trainingObj = new Training();
        trainingObj.setUserId(1);
        trainingObj.setName("Monday usual");
        trainingObj.setDescription("Really cool training");
        trainingDao.addUserTraining(trainingObj, null);
    }

    @Test
    public void addTraining_success() throws IOException {
        Training trainingObj = new Training();
        trainingObj.setUserId(1);
        trainingObj.setName("Friday crappy");
        trainingObj.setDescription("Very hard training");
        trainingDao.addUserTraining(trainingObj,
                    JdbcTrainingDaoTest.class.getResourceAsStream(dataFileString));
    }
}
