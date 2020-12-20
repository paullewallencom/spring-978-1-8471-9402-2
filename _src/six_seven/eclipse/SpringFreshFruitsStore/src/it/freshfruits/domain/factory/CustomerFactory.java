package it.freshfruits.domain.factory;

import it.freshfruits.domain.entity.Customer;

public interface CustomerFactory {

    public Customer getCustomer(String idCustomer);

    public Customer getCurrentCustomer();
}
