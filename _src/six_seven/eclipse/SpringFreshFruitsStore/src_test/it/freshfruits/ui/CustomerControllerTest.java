package it.freshfruits.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.freshfruits.conf.dbunit.DBCustomer;
import it.freshfruits.conf.dbunit.DBOrder;
import it.freshfruits.conf.dbunit.DBOrderItems;
import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.CustomerImpl;
import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.vo.OrderItemImpl;
import it.freshfruits.util.Constants;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public class CustomerControllerTest {

    private CustomerController customerController;
    private Customer customer;
    private FruitType fruit;

    @Before
    public void setup() {
        customerController = new CustomerController();
    }

    @After
    public void tearDown() {
        customerController = null;
        customer = null;
        fruit = null;
    }

    @Test
    public void create() throws Exception {
        customer = new CustomerImpl.Builder("max", "26").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setAttribute(Constants.CUSTOMER, customer);
        ModelAndView modelAndView = customerController.create(req);
        assertEquals(modelAndView.getViewName(), "customer/create");
        assertTrue(modelAndView.getModel().containsKey("result"));
    }

    @Test
    public void items() throws Exception {
        customer = new CustomerImpl.Builder("max", "26").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        assertTrue(customer.createOrder());
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setAttribute(Constants.CUSTOMER, customer);
        ModelAndView modelAndView = customerController.items(req);
        assertEquals(modelAndView.getViewName(), "customer/items");
        assertTrue(modelAndView.getModel().containsKey("items"));
    }

    @Test
    public void order() throws Exception {
        customer = new CustomerImpl.Builder("max", "26").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        assertTrue(customer.createOrder());
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setAttribute(Constants.CUSTOMER, customer);
        ModelAndView modelAndView = customerController.order(req);
        assertEquals(modelAndView.getViewName(), "customer/order");
        assertTrue(modelAndView.getModel().containsKey("order"));
    }

    @Test
    public void remove() throws Exception {
        customer = new CustomerImpl.Builder("max", "26").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        assertTrue(customer.createOrder());
        fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.30")).build();
        assertTrue(customer.getOrder().addOrderItem(new OrderItemImpl.Builder(fruit, 2, customer.getOrder().getId().toString()).build()));
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setAttribute(Constants.CUSTOMER, customer);
        ModelAndView modelAndView = customerController.remove(fruit.getId().toString(), req);
        assertEquals(modelAndView.getViewName(), "customer/items");
        assertTrue(modelAndView.getModel().containsKey("items"));
    }

    @Test
    public void save() throws Exception {
        DBCustomer dbCustomer = new DBCustomer();
        DBOrder dbOrder = new DBOrder();
        DBOrderItems dbItems = new DBOrderItems();
        dbCustomer.prepareDb();

        customer = new CustomerImpl.Builder("max", "26").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        assertTrue(customer.createOrder());
        fruit = new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).build();
        customer.getOrder().addOrderItem(new OrderItemImpl.Builder(fruit, 13, customer.getOrder().getId().toString()).build());
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setAttribute(Constants.CUSTOMER, customer);
        ModelAndView modelAndView = customerController.save(req);
        assertEquals(modelAndView.getViewName(), "customer/save");
        assertTrue(modelAndView.getModel().containsKey("result"));

        dbItems.cleanDb();
        dbOrder.cleanDb();
        dbCustomer.cleanDb();
        dbCustomer = null;
    }
}
