package it.freshfruits.utils;

import it.freshfruits.util.ValidationUtils;
import junit.framework.TestCase;

public class ValidationUtilsTest extends TestCase {

    public void testId() {
        boolean result = true;
        try {
            ValidationUtils.validateId(new Integer(1).toString());
        } catch (IllegalArgumentException e) {

            result = false;

        }
        assertTrue(result);

    }

    public void testNullId() {
        boolean result = false;
        try {
            ValidationUtils.validateId(null);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("id argument null")) {
                result = true;
            }
        }
        assertTrue(result);

    }

    public void testWongIntegerId() {
        boolean result = false;
        try {
            ValidationUtils.validateId(new Integer(-1).toString());
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("id argument < 0 :-1")) {
                result = true;
            }
        }
        assertTrue(result);

    }

    public void testWongStringId() {
        boolean result = false;
        try {
            ValidationUtils.validateId("-2");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("id argument < 0 :-2")) {
                result = true;
            }

        }
        assertTrue(result);

    }

    public void testWongLenghtStringId() {
        boolean result = false;
        try {
            ValidationUtils.validateId("");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("id argument  lenght < 1")) {
                result = true;
            }

        }
        assertTrue(result);

    }

    public void testWongNumberFormatId() {
        boolean result = false;
        try {
            ValidationUtils.validateId("lf0");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("id bad number format :lf0")) {
                result = true;
            }

        }
        assertTrue(result);
    }

}
