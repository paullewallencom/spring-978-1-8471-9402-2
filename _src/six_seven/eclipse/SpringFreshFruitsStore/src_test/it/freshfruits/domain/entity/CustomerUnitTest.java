package it.freshfruits.domain.entity;

import static org.junit.Assert.assertEquals;
import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.CustomerImpl;
import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.AddressImpl;
import it.freshfruits.domain.vo.ContactInformation;
import it.freshfruits.domain.vo.ContactInformationImpl;

import org.junit.Test;

public class CustomerUnitTest {

    @Test
    public void testBuilder() {

        Customer customer = new CustomerImpl.Builder("max", "1").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        assertEquals(customer.getId().toString(), "1");
        assertEquals(customer.getName(), "max");

        Address address = customer.getAddress();
        assertEquals(address.getCity(), "Cagliari");
        assertEquals(address.getState(), "Italy");
        assertEquals(address.getStreet(), "Viale Europa");

        ContactInformation contact = customer.getContact();
        assertEquals(contact.getEmail(), "foo[at]yahoo[dot]it");
        assertEquals(contact.getFaxNumber(), "");
        assertEquals(contact.getMobilePhoneNumber(), "3391234567");
        assertEquals(contact.getPhoneNumber(), "+39070123456");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongLenghtAddress() {

        new CustomerImpl.Builder("max", "1").address("Vi", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongLenghtCity() {

        new CustomerImpl.Builder("max", "1").address("Via", "Ca", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongLenghtState() {

        new CustomerImpl.Builder("max", "1").address("Via", "Cagliari", "It").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongLenghtPhone() {

        new CustomerImpl.Builder("max", "1").address("Via", "Cagliari", "Italia").contactInfo("+39", "3391234567", "", "foo[at]yahoo[dot]it").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongLenghtMobilePhone() {

        new CustomerImpl.Builder("max", "1").address("Via", "Cagliari", "Italia").contactInfo("+39070123456", "339", "", "foo[at]yahoo[dot]it").build();

    }

    @Test
    public void testModifyAddressCorrect() {

        Customer customer = new CustomerImpl.Builder("max", "1").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        Address newAddress = new AddressImpl.Builder("Viale Europa", "Cagliari", "Italy").build();
        customer.modifyAddress(newAddress);
    }

    @Test
    public void testModifyAddressWrong() {

        Customer customer = new CustomerImpl.Builder("max", "1").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        Address newAddress = new AddressImpl.Builder("Viale Europa", "Cagliari", "Italy").build();
        customer.modifyAddress(newAddress);
    }

    @Test
    public void testModifyContactInformationCorrect() {

        Customer customer = new CustomerImpl.Builder("max", "1").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        ContactInformation newContactInformation = new ContactInformationImpl.Builder("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        customer.modifyContactInformation(newContactInformation);
    }

    @Test
    public void testModifyContactInformationWrong() {

        Customer customer = new CustomerImpl.Builder("max", "1").address("Viale Europa", "Cagliari", "Italy").contactInfo("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();

        ContactInformation newContactInformation = new ContactInformationImpl.Builder("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        customer.modifyContactInformation(newContactInformation);
    }

}
