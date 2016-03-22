package ru.mipt.snp.dao.jdbc.common;

/**
 * <p></p>
 *
 * @author Kirill Tsibriy
 * @since 23.04.2009 2:22:12
 */
public class QueryFactory {
    private String id;
    private String sqlBase;

    public QueryFactory(String id) {
        this.id = id;
    }

    public void setSqlBase(String sqlBase) {
        this.sqlBase = sqlBase;
    }

    public String getId() {
        return id;
    }

    public Query create() {
        return new Query(sqlBase);
    }
}
