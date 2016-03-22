package ru.mipt.snp.dao.jdbc.common;

import java.util.Map;

/**
 * <p></p>
 *
 * Created by Kirill Tsibriy on 01.03.2009
 */
public class QueryHolder {
    private final Map<String, QueryFactory> queries;

    public QueryHolder(Map<String, QueryFactory> queries) {
        this.queries = queries;
    }

    public Query create(final String key) {
        final QueryFactory queryFactory = queries.get(key);

        if (queryFactory == null) {
            throw new IllegalArgumentException(String.format("Query is not registered for key %1$s", key));
        }

        return queryFactory.create();
    }
}
