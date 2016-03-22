package ru.mipt.snp.dao;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/08/2009  15:55
 */
public class JdbcDaoTest {

    protected TrainingDao trainingDao;
    protected String dataFileString;

    @Before
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:ru/mipt/snp/dao/jdbc/jdbc-dao.xml",
                              "classpath:ru/mipt/snp/dao/test-context.xml"});

        trainingDao = (TrainingDao) context.getBean("trainingDao");
        dataFileString = (String) context.getBean("testDataFile"); 
    }
}
