package it.freshfruits.domain.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.freshfruits.SpringFactory;
import it.freshfruits.conf.dbunit.DBCustomer;
import it.freshfruits.domain.factory.CustomerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class CustomerFactoryTest {

    @Before
    public void setUp() throws Exception {
        ctx = SpringFactory.getXmlWebApplicationContext();
        factory = (CustomerFactory) ctx.getBean("customerFactory");
    }

    @After
    public void tearDown() throws Exception {
        db.cleanDb();
        db = null;
        ctx = null;
        factory = null;
    }

    @Test
    public void testGetCustomer() {
        db.prepareDb();
        assertNotNull(factory.getCustomer("26"));
        db.cleanDb();
    }

    @Test
    public void testCurrentCustomerWithOutAuth() {

        boolean result = false;
        try {
            factory.getCurrentCustomer();
        } catch (NullPointerException e) {
            result = true;
        }
        assertTrue(result);

    }

    private DBCustomer db = new DBCustomer();
    private CustomerFactory factory;
    private ApplicationContext ctx;
}
