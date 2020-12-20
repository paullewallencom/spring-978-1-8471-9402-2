package it.freshfruits.application.repository;

import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.CustomerImpl;
import it.freshfruits.domain.entity.CustomerView;
import it.freshfruits.domain.vo.CustomerMap;
import it.freshfruits.util.Constants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("customerRepository")
public class CustomerRepositoryImpl extends SqlMapClientTemplate implements CustomerRepository {

    public Boolean saveCustomer(Customer customer) {

        if (customer.getId().toString().equals(Constants.ID_NEW)) {

            return !insertCustomer(customer).equals(null) ? true : false;

        } else {

            return updateCustomer(customer);
        }
    }

    public Boolean deleteCustomer(String id) {
        return delete("deleteCustomerVO", Integer.valueOf(id)) == 1 ? true : false;
    }

    public String insertCustomer(Customer customer) {
        return insert("insertCustomerVO", new CustomerMap(customer)).toString();
    }

    public Boolean isPresent(String name) {
        return queryForObject("selectIdCustomerByName", name) != null ? true : false;
    }

    public Customer selectCustomer(String id) {
        CustomerMap vo = (CustomerMap) queryForObject("selectCustomerVO", Integer.valueOf(id));
        return new CustomerImpl.Builder(vo.getName(), vo.getId().toString(), vo.getAddress(), vo.getContactInformation()).build();
    }

    public Boolean updateCustomer(Customer customer) {
        return update("updateCustomerVO", new CustomerMap(customer)) == 1 ? true : false;
    }

    public Boolean disableCustomer(String id) {
        return update("disableCustomer", Integer.valueOf(id)) == 1 ? true : false;
    }

    @SuppressWarnings("unchecked")
    public List<CustomerView> selectCustomers() {
        return queryForList("selectCustomers");
    }

    @SuppressWarnings("unchecked")
    public List<CustomerView> selectDisabledCustomers() {
        return queryForList("selectDisabledCustomers");
    }

    public String getIdCustomer(String username) {
        return queryForObject("selectIdByUsername", username).toString();
    }

    @Autowired @Override
    public void setSqlMapClient(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }
}