package it.freshfruits.domain.vo;

import static org.junit.Assert.assertEquals;
import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.AddressImpl;

import org.junit.Test;

public class AddressUnitTest {

    @Test
    public void testConstructorCorrect() {
        Address address = new AddressImpl.Builder("Viale Europa", "Cagliari", "Italy").build();
        assertEquals(address.getCity(), "Cagliari");
        assertEquals(address.getState(), "Italy");
        assertEquals(address.getStreet(), "Viale Europa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStreet() {
        new AddressImpl.Builder(null, "Cagliari", "Italy").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullCity() {
        new AddressImpl.Builder("Viale Europa", null, "Italy").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullState() {
        new AddressImpl.Builder("Viale Europa", "Cagliari", null).build();
    }

}