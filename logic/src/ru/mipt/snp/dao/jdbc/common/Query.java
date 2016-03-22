package ru.mipt.snp.dao.jdbc.common;

/**
 * <p>Implementation of query</p>
 *
 * Created by Kirill Tsibriy on 01.03.2009
 */
public class Query {
    private final String sqlBase;

    public Query(String sqlBase) {
        this.sqlBase = sqlBase;
    }

    public String toSql() {
        return sqlBase;
    }
}
