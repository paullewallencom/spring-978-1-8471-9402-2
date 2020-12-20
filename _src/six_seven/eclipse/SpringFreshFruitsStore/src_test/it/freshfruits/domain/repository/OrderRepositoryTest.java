package it.freshfruits.domain.repository;

import static org.junit.Assert.*;
import it.freshfruits.SpringFactory;
import it.freshfruits.application.repository.OrderRepository;
import it.freshfruits.conf.dbunit.DBCustomer;
import it.freshfruits.conf.dbunit.DBFruitType;
import it.freshfruits.conf.dbunit.DBOrder;
import it.freshfruits.conf.dbunit.DBOrderItems;
import it.freshfruits.domain.entity.Order;
import it.freshfruits.domain.entity.OrderImpl;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.util.Constants;
import it.freshfruits.util.DateTimeUtils;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class OrderRepositoryTest {

    @Before
    public void setUp() throws Exception {
        ctx = SpringFactory.getXmlWebApplicationContext();
        repo = (OrderRepository) ctx.getBean("orderRepository");
    }

    @After
    public void tearDown() throws Exception {
        ctx = null;
        repo = null;
    }

    @Test
    public void testInsertOrder() {
        DBCustomer dbCustomer = new DBCustomer();
        DBOrder dbOrders = new DBOrder();
        dbCustomer.prepareDb();

        Order order = new OrderImpl.Builder(Constants.ID_NEW, DateTimeUtils.getDateNowToNextDays(2).toDate(), "26").amount(new BigDecimal("5000")).build();
        try {
            String id = repo.insertOrder(order);
            Order retrieve = repo.getOrder(id, "26");
            assertNotNull(retrieve);
        } catch (Exception e) {
            fail("exception unexpected");
        }
        dbOrders.cleanDb();
        dbCustomer.cleanDb();
    }

    @Test
    public void testUpdateOrder() {
        DBCustomer dbCustomer = new DBCustomer();
        DBOrder dbOrders = new DBOrder();

        dbCustomer.prepareDb();
        dbOrders.prepareDb();

        Order order = new OrderImpl.Builder("34", DateTimeUtils.getDateNowToNextDays(2).toDate(), "26").amount(new BigDecimal("5000")).build();

        assertTrue(repo.updateOrder(order));
        Order retrieve = repo.getOrder("34", "26");
        assertNotNull(retrieve);

        dbOrders.cleanDb();
        dbCustomer.cleanDb();
    }

    @Test
    public void testSelectOrders() {
        DBCustomer dbCustomer = new DBCustomer();
        DBOrder dbOrders = new DBOrder();

        dbCustomer.prepareDb();
        dbOrders.prepareDb();

        assertTrue(repo.getOrders("26").size() == 1);
        assertTrue(repo.getOrders("27").size() == 0);

        dbOrders.cleanDb();
        dbCustomer.cleanDb();
    }

    @Test
    public void testSelectOrder() {
        DBCustomer dbCustomer = new DBCustomer();
        dbCustomer.prepareDb();
        DBFruitType dbFruit = new DBFruitType();
        dbFruit.prepareDb();
        DBOrder dbOrder = new DBOrder();
        dbOrder.prepareDb();
        DBOrderItems dbOrderItems = new DBOrderItems();
        dbOrderItems.prepareDb();

        Order order = repo.getOrder("34", "26");
        assertEquals(order.getNumberItems().toString(), "1");
        assertTrue(order.getNumberItems() == 1);

        for (OrderItem item : order.getOrderItems()) {
            assertEquals(item.getQuantity(), new Integer(8));
            assertEquals(item.getAmountItem(), new BigDecimal("9.6"));
        }

        dbOrderItems.cleanDb();
        dbOrder.cleanDb();
        dbFruit.cleanDb();
        dbCustomer.cleanDb();
    }

    private OrderRepository repo;
    private ApplicationContext ctx;

}
