package it.freshfruits.domain.vo;

import it.freshfruits.domain.entity.Customer;

public class CustomerMap {

    public CustomerMap() {
    }

    public CustomerMap(Customer customer) {

        this.city = customer.getAddress().getCity();
        this.state = customer.getAddress().getState();
        this.street = customer.getAddress().getStreet();

        this.email = customer.getContact().getEmail();
        this.faxNumber = customer.getContact().getFaxNumber();
        this.mobilePhoneNumber = customer.getContact().getMobilePhoneNumber();
        this.phoneNumber = customer.getContact().getPhoneNumber();
        this.id = new Integer(customer.getId());
        this.name = customer.getName();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return new AddressImpl.Builder(street, city, state).build();
    }

    public ContactInformation getContactInformation() {
        return new ContactInformationImpl.Builder(phoneNumber, mobilePhoneNumber, faxNumber, email).build();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Address fields
    private String street = "";
    private String city = "";
    private String state = "";

    // ContactInformation
    private String phoneNumber = "";
    private String mobilePhoneNumber = "";
    private String faxNumber = "";
    private String email = "";

    private String name = "";
    private Integer id = 0;
}
