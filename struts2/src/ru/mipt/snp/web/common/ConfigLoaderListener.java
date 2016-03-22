package ru.mipt.snp.web.common;

import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

/**
 * <p>Listener to configure startup process for the application</p>
 *
 * @author Kirill Tsibriy
 * @since 08.05.2009
 */
public class ConfigLoaderListener implements ServletContextListener {
    public static final String LOG4J_CONFIG_FILE = "log4j.configuration";

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext sc = servletContextEvent.getServletContext();
        
        configureLogging(sc);
    }

    private void configureLogging(ServletContext sc) {
        DOMConfigurator.configure(sc.getRealPath(sc.getInitParameter(LOG4J_CONFIG_FILE)));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
