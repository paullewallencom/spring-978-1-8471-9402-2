package it.freshfruits.domain.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.freshfruits.SpringFactory;
import it.freshfruits.application.repository.FruitTypeRepository;
import it.freshfruits.conf.dbunit.DBCustomer;
import it.freshfruits.conf.dbunit.DBFruitType;
import it.freshfruits.conf.dbunit.DBOrder;
import it.freshfruits.conf.dbunit.DBOrderItems;
import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.Order;
import it.freshfruits.domain.factory.CustomerFactory;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.OrderItemImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class OrderItemTest {

    @Before
    public void setUp() throws Exception {
        ctx = SpringFactory.getXmlWebApplicationContext();
        customerFactory = (CustomerFactory) ctx.getBean("customerFactory");
        fruitRepository = (FruitTypeRepository) ctx.getBean("fruitRepository");
    }

    @After
    public void tearDown() throws Exception {
        ctx = null;
        customerFactory = null;
        fruitRepository = null;
    }

    @Test
    public void testOrderCustomer() {
        DBCustomer dbCustomer = new DBCustomer();
        DBFruitType dbFruit = new DBFruitType();
        DBOrder dbOrder = new DBOrder();
        DBOrderItems dbOrderItems = new DBOrderItems();

        dbCustomer.prepareDb();
        dbFruit.prepareDb();

        Customer customer = customerFactory.getCustomer("26");
        assertNotNull(customer);

        customer.createOrder();

        Order order = customer.getOrder();
        assertNotNull(order);

        OrderItem item = new OrderItemImpl.Builder(fruitRepository.getFruitType(1), 9, order.getId().toString()).build();

        assertTrue(order.addOrderItem(item));

        assertEquals(order.getOrderAmount().toString(), "10.8");

        assertTrue(customer.saveOrder());

        dbFruit.cleanDb();
        dbOrderItems.cleanDb();
        dbOrder.cleanDb();
        dbCustomer.cleanDb();

    }

    private CustomerFactory customerFactory;
    private FruitTypeRepository fruitRepository;
    private ApplicationContext ctx;
}
