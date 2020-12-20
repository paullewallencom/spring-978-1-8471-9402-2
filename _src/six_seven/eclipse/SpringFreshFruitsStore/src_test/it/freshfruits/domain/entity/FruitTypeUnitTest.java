package it.freshfruits.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;

import java.math.BigDecimal;

import org.junit.Test;

public class FruitTypeUnitTest {

    @Test
    public void testBasicBuilder() {
        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).build();

        assertEquals(fruit.getId().toString(), "10");
        assertEquals(fruit.getName(), "orange");
        assertEquals(fruit.getColor(), "");
        assertEquals(fruit.getFlavour(), "");
        assertEquals(fruit.getLocation(), "");
        assertTrue(fruit.getPrice().toString().equals("0.30"));
    }

    @Test
    public void testFullBuilder() {

        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("red").flavour("sweet").location("Italy").build();

        assertEquals(fruit.getId().toString(), "10");
        assertEquals(fruit.getName(), "orange");
        assertEquals(fruit.getColor(), "red");
        assertEquals(fruit.getFlavour(), "sweet");
        assertEquals(fruit.getLocation(), "Italy");
        assertTrue(fruit.getPrice().toString().equals("0.30"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {

        new FruitTypeImpl.Builder(null, new Integer(10), new BigDecimal("0.30")).color("red").flavour("sweet").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPrice() {

        new FruitTypeImpl.Builder("orange", new Integer(10), null).color("red").flavour("sweet").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullColor() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color(null).flavour("sweet").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFlavour() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("red").flavour(null).location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullLocation() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("red").flavour("sweet").location(null).build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongPrice() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("-0.01")).color("red").flavour("sweet").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongName() {

        new FruitTypeImpl.Builder("or", new Integer(10), new BigDecimal("0.30")).color("red").flavour("sweet").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongColor() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("re").flavour("sweet").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongFlavour() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("red").flavour("sw").location("Italy").build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongLocation() {

        new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("red").flavour("sweet").location("It").build();

    }
}