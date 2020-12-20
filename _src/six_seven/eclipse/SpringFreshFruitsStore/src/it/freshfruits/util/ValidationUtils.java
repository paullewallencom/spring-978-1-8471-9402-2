package it.freshfruits.util;

import it.freshfruits.domain.entity.FruitType;

import java.math.BigDecimal;
import java.util.Date;

public class ValidationUtils {

    public static void validateId(String id) {
        if (id == null)
            throw new IllegalArgumentException("id argument null");
        if (id.length() < 1)
            throw new IllegalArgumentException("id argument  lenght < 1");
        try {
            Integer idInt = Integer.valueOf(id);
            if (idInt < 0)
                throw new IllegalArgumentException("id argument < 0 :" + id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("id bad number format :" + id);
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 0 && phoneNumber.length() < 6)
            throw new IllegalArgumentException("phoneNumber argument < 6 :" + phoneNumber);
    }

    public static void validateMobilePhoneNumber(String mobilePhoneNumber) {
        if (mobilePhoneNumber == null || mobilePhoneNumber.length() != 0 && mobilePhoneNumber.length() < 10)
            throw new IllegalArgumentException("mobilePhoneNumber argument < 10 :" + mobilePhoneNumber);
    }

    public static void validateFaxNumber(String faxNumber) {
        if (faxNumber == null || faxNumber.length() != 0 && faxNumber.length() < 6)
            throw new IllegalArgumentException("faxNumber argument < 6 :" + faxNumber);
    }

    public static void validateEmail(String email) {
        if (email == null || email.length() < 6)
            throw new IllegalArgumentException("email argument < 6 :" + email);
    }

    public static void validateStreet(String street) {
        if (street == null || street.length() < 3)
            throw new IllegalArgumentException("street argument < 3 chars:" + street);
    }

    public static void validateCity(String city) {
        if (city == null || city.length() < 3)
            throw new IllegalArgumentException("city argument < 3 chars:" + city);
    }

    public static void validateState(String state) {
        if (state == null || state.length() < 3)
            throw new IllegalArgumentException("state argument < 3 chars:" + state);
    }

    public static void validateDate(Date dateOrder) {
        if (dateOrder == null) {
            throw new IllegalArgumentException("dateOrder not correct");
        }
    }

    public static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(new BigDecimal("0")) <= 0)
            throw new IllegalArgumentException("amount < 0:" + amount);
    }

    public static void validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 0f)
            throw new IllegalArgumentException("quantity < 0 :" + quantity);
    }

    public static void validateFruitType(FruitType fruit) {
        if (fruit == null)
            throw new IllegalArgumentException("fruitType null");
    }

}
