package ru.mipt.snp.dao.jdbc.mapping;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Maxim Galushka
 * @since 30.12.2009
 */
public class TrainingCallbackHandler  implements RowCallbackHandler {

    
    @Override
    public void processRow(ResultSet rs) throws SQLException {
        
    }
}
