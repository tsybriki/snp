package ru.mipt.snp.dao.jdbc.common;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>Implementation of handler for parsing xml config using SAX</p>
 *
 * @author Kirill Tsibriy
 * @since 23.04.2009 2:55:42
 */
class QueryFileHandler extends DefaultHandler {
    private Map<String, QueryFactory> queries = new HashMap<String, QueryFactory>();

    public static final String QUERIES_ELEMENT = "queries";
    public static final String QUERY_ELEMENT = "query";
    public static final String QUERY_ID_ATTRIBUTE = "id";

    private QueryFactory currentQuery;
    private StringBuilder currentSql;

    public Map<String, QueryFactory> getQueries() {
        return queries;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (QUERY_ELEMENT.equals(localName)) {
            currentQuery = new QueryFactory(attributes.getValue(QUERY_ID_ATTRIBUTE));
            currentSql = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (QUERY_ELEMENT.equals(localName)) {
            currentQuery.setSqlBase(currentSql.toString());
            queries.put(currentQuery.getId(), currentQuery);
            currentQuery = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentQuery != null) {
            currentSql.append(ch, start, length);
        }
    }
}
