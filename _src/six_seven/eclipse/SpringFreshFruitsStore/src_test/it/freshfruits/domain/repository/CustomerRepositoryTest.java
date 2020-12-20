package it.freshfruits.domain.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.freshfruits.SpringFactory;
import it.freshfruits.application.repository.CustomerRepository;
import it.freshfruits.conf.dbunit.DBCustomer;
import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.CustomerImpl;
import it.freshfruits.domain.entity.CustomerView;
import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.ContactInformation;
import it.freshfruits.util.Constants;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;

public class CustomerRepositoryTest {

    @Before
    public void setUp() throws Exception {
        ctx = SpringFactory.getXmlWebApplicationContext();
        repo = (CustomerRepository) ctx.getBean("customerRepository");
    }

    @After
    public void tearDown() throws Exception {
        db.cleanDb();
        ctx = null;
        repo = null;
    }

    @Test
    public void testInsertCustomer() {

        Customer customer = new CustomerImpl.Builder("max", Constants.ID_NEW).address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        try {
            repo.insertCustomer(customer);
        } catch (DataAccessException e) {
            fail("unexpected exception");
        }
        return;
    }

    @Test
    public void testSaveCustomer() {

        Customer customer = new CustomerImpl.Builder("max", Constants.ID_NEW).address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        assertTrue(repo.saveCustomer(customer));

    }

    @Test
    public void testSelectCustomer() {
        db.prepareDb();
        Customer customer = repo.selectCustomer("26");
        Address address = customer.getAddress();
        ContactInformation contact = customer.getContact();

        assertTrue(customer.getName().equals("max"));
        assertTrue(customer.getId().toString().equals("26"));

        assertTrue(address.getCity().equals("Cagliari"));
        assertTrue(address.getState().equals("Italy"));
        assertTrue(address.getStreet().equals("Viale Europa"));

        assertTrue(contact.getEmail().equals("foo[at]yahoo[dot]it"));
        assertTrue(contact.getFaxNumber().equals(""));
        assertTrue(contact.getMobilePhoneNumber().equals("3391234567"));
        assertTrue(contact.getPhoneNumber().equals("+39070123456"));

        db.cleanDb();
    }

    @Test
    public void testSelectCustomers() {
        db.prepareDb();
        List<CustomerView> customers = repo.selectCustomers();
        assertTrue(customers.size() == 2);
    }

    @Test
    public void testSelectDisabledCustomers() {
        db.prepareDb();
        assertTrue(repo.disableCustomer("26"));
        List<CustomerView> customers = repo.selectCustomers();
        assertTrue(customers.size() == 1);
    }

    @Test
    public void testIsPresent() {
        db.prepareDb();
        assertTrue(!repo.isPresent("mike"));
        assertTrue(repo.isPresent("max"));
    }

    @Test
    public void testDeleteCustomer() {
        db.prepareDb();
        assertTrue(repo.deleteCustomer("26"));
    }

    @Test
    public void testdisableCustomer() {
        db.prepareDb();
        assertTrue(repo.disableCustomer("26"));
    }

    @Test
    public void testUpdateCustomer() {
        db.prepareDb();
        Customer customer = new CustomerImpl.Builder("max", "26").address("Via Scano", "Monserrato", "Italy").contactInfo("+39070654321", "3397654321", "07012345678", "foo[at]yahoo[dot]com").build();
        assertTrue(repo.updateCustomer(customer));
    }

    private DBCustomer db = new DBCustomer();
    private CustomerRepository repo;
    private ApplicationContext ctx;

}
