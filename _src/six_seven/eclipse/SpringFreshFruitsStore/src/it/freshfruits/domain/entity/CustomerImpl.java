package it.freshfruits.domain.entity;

import it.freshfruits.application.repository.CustomerRepository;
import it.freshfruits.application.repository.OrderRepository;
import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.AddressImpl;
import it.freshfruits.domain.vo.ContactInformation;
import it.freshfruits.domain.vo.ContactInformationImpl;
import it.freshfruits.util.Constants;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

@Configurable(dependencyCheck = true, autowire = Autowire.BY_TYPE)
public class CustomerImpl implements Customer, Serializable {

    public Boolean createOrder() {
        Boolean result = false;
        if (order == null) {
            order = new OrderImpl.Builder(Constants.ID_NEW, new Date(), id.toString()).build();
            result = true;
        }
        return result;
    }

    public Boolean saveCustomer() {
        return customerRepository.saveCustomer(this);
    }

    public Address getAddress() {
        return address;
    }

    public ContactInformation getContact() {
        return contactInformation;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void modifyContactInformation(ContactInformation contact) {
        contactInformation = contact;
    }

    public void modifyAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orderRepository.getOrders(id.toString());
    }

    public Order getOrder() {
        return order;
    }

    public Boolean saveOrder() {
        return orderRepository.saveOrder(order);
    }

    public String toString() {
        return new StringBuilder().append("\nid:").append(id).append("\nname:").append(name).append("\n<-address->").append(address).append("\n<-contactInformation->").append(contactInformation)
                .toString();
    }

    public static class Builder {
        // Required parameters
        private String name = "";
        private Integer id = 0;

        // Optional parameters
        private Address address;
        private ContactInformation contactInformation;
        private OrderRepository orderRepository;
        private CustomerRepository customerRepository;

        public Builder(String name, String id) {
            this.name = name;
            this.id = Integer.valueOf(id);
        }

        public Builder(String name, String id, Address address, ContactInformation contact) {
            this.name = name;
            this.id = Integer.valueOf(id);
            this.address = address;
            this.contactInformation = contact;
        }

        public Builder address(String street, String city, String state) {
            address = new AddressImpl.Builder(street, city, state).build();
            return this;
        }

        public Builder contactInfo(String phoneNumber, String mobilePhoneNumber, String faxNumber, String email) {
            contactInformation = new ContactInformationImpl.Builder(phoneNumber, mobilePhoneNumber, faxNumber, email).build();
            return this;
        }

        public Builder orderRepository(OrderRepository orderRepository) {
            this.orderRepository = orderRepository;
            return this;
        }

        public Builder customerRepository(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
            return this;
        }

        public CustomerImpl build() {
            return new CustomerImpl(this);
        }
    }

    private CustomerImpl(Builder builder) {
        id = builder.id;
        name = builder.name;
        address = builder.address;
        contactInformation = builder.contactInformation;
        orderRepository = builder.orderRepository;
        customerRepository = builder.customerRepository;
    }

    @Required
    public void setCustomerRepository(@Qualifier("customerRepository") CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Required
    public void setOrderRepository(@Qualifier("orderRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private Address address;
    private Order order;
    private ContactInformation contactInformation;
    private String name;
    private Integer id;
    private static final long serialVersionUID = 6512264975502119631L;
}