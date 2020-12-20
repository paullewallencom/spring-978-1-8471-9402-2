package it.freshfruits.application.repository;

import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.CustomerView;

import java.util.List;

public interface CustomerRepository {

    public String getIdCustomer(String username);

    public Boolean saveCustomer(Customer customer);

    public String insertCustomer(Customer customer);

    public Boolean updateCustomer(Customer customer);

    public Boolean disableCustomer(String id);

    public Boolean deleteCustomer(String id);

    public Customer selectCustomer(String id);

    public List<CustomerView> selectCustomers();

    public List<CustomerView> selectDisabledCustomers();

    public Boolean isPresent(String name);
}
