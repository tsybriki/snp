package ru.mipt.snp.dao.jdbc.common;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * <p>Spring factory bean for query holder</p>
 *
 * @author Kirill Tsibriy
 * @since 23.04.2009 2:42:48
 */
public class QueryHolderFactoryBean implements FactoryBean {
    private Resource configFile;

    public Object getObject() throws Exception {
        Assert.notNull(configFile, "Please provide configuration file");

        final QueryFileHandler handler = new QueryFileHandler();

        final XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(configFile.getInputStream()));

        return new QueryHolder(handler.getQueries());
    }

    public Class getObjectType() {
        return QueryHolder.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setConfigFile(Resource configFile) {
        this.configFile = configFile;
    }
}
