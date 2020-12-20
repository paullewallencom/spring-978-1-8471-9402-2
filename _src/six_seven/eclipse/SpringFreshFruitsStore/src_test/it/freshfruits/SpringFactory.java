package it.freshfruits;

import javax.servlet.ServletContext;

import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringFactory {

    public static void setUpXmlWebApplicationContext() {
        if (_xmlWebCtx == null) {
            ServletContext servletContext = new MockServletContext("file:src_test/it/freshfruits/conf");
            String[] paths = { "sffs-application.xml", "sffs-aop-domain.xml" };
            _xmlWebCtx = new XmlWebApplicationContext();
            _xmlWebCtx.setConfigLocations(paths);
            _xmlWebCtx.setServletContext(servletContext);
            _xmlWebCtx.refresh();
            System.out.println("Start XmlWebApplicationContext");
        }
    }

    public static Object getBean(String name) {
        return _xmlWebCtx.getBean(name);
    }

    public static XmlWebApplicationContext getXmlWebApplicationContext() {
        if (_xmlWebCtx == null) {
            setUpXmlWebApplicationContext();
        }
        return _xmlWebCtx;

    }

    public static void destroyXmlWebApplicationContext() {

        _xmlWebCtx.close();
        _xmlWebCtx = null;
        System.out.println("XmlWebApplicationContext Stop");
    }

    private static XmlWebApplicationContext _xmlWebCtx;
}
