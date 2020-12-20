package it.freshfruits.domain.vo;

import static org.junit.Assert.assertEquals;
import it.freshfruits.domain.vo.ContactInformation;
import it.freshfruits.domain.vo.ContactInformationImpl;

import org.junit.Test;

public class ContactInformationUnitTest {

    @Test
    public void testConstructorCorrect() {

        ContactInformation contact = new ContactInformationImpl.Builder("+39070123456", "3391234567", "", "foo[at]yahoo[dot]it").build();
        assertEquals(contact.getEmail(), "foo[at]yahoo[dot]it");
        assertEquals(contact.getFaxNumber(), "");
        assertEquals(contact.getMobilePhoneNumber(), "3391234567");
        assertEquals(contact.getPhoneNumber(), "+39070123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEmail() {

        new ContactInformationImpl.Builder("+39070123456", "3391234567", "", null).build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPhoneNumber() {

        new ContactInformationImpl.Builder(null, "3391234567", "", "foo[at]yahoo[dot]it").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMobilePhoneNumber() {

        new ContactInformationImpl.Builder("+39070123456", null, "", "foo[at]yahoo[dot]it").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFaxNumber() {

        new ContactInformationImpl.Builder("+39070123456", "3391234567", null, "foo[at]yahoo[dot]it").build();

    }
}
