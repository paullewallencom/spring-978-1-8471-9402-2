package it.freshfruits.domain.entity;

import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.ContactInformation;

public interface CustomerView {

    public String getName();

    public Integer getId();

    public Address getAddress();

    public ContactInformation getContactInformation();
}