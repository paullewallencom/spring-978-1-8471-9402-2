package it.freshfruits.domain.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.freshfruits.application.repository.CustomerRepository;
import it.freshfruits.domain.entity.Customer;
import it.freshfruits.security.SecurityUtils;

/* good candidate for customerCache
*/
@Component("customerFactory")
public class CustomerFactoryImpl implements CustomerFactory {

    public Customer getCurrentCustomer() {
        return customerRepository.selectCustomer(SecurityUtils.getIdCustomer());
    }

    public Customer getCustomer(String idCustomer) {
        return customerRepository.selectCustomer(idCustomer);
    }

    @Autowired
    private CustomerRepository customerRepository;
}
