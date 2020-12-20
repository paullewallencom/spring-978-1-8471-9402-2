package it.freshfruits.domain.entity;

import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.ContactInformation;

import java.util.List;

public interface Customer extends NamedEntity {

    public Address getAddress();

    public ContactInformation getContact();

    public void modifyContactInformation(ContactInformation contact);

    public void modifyAddress(Address address);

    public Boolean saveCustomer();

    public Boolean createOrder();

    public Boolean saveOrder();

    public Order getOrder();

    public List<Order> getOrders();
}
