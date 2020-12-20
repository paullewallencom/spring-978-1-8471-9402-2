package it.freshfruits.domain.entity;

import static org.junit.Assert.*;
import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.entity.OrderImpl;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.OrderItemImpl;
import it.freshfruits.util.DateTimeUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class OrderUnitTest {

    @Test
    public void testBasicBuilder() {

        Date date = DateTimeUtils.getDateNowToNextDays(2).toDate();
        OrderImpl order = new OrderImpl.Builder(new Integer(11).toString(), date, "2").build();
        assertEquals(order.getId().toString(), "11");
        assertEquals(order.getDateOrder(), date);

    }

    @Test
    public void testFullBuilder() {

        Date now = new Date();
        OrderImpl order = new OrderImpl.Builder(new Integer(11).toString(), now, "2").amount(new BigDecimal("5000")).build();
        assertEquals(order.getId().toString(), "11");
        assertEquals(order.getDateOrder(), now);
        assertEquals(order.getOrderAmount().toString(), "5000");
    }

    @Test
    public void testFruitItems() {

        Date date = DateTimeUtils.getDateNowToNextDays(2).toDate();
        OrderImpl order = new OrderImpl.Builder(new Integer(11).toString(), date, "2").build();

        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();
        OrderItem item = new OrderItemImpl.Builder(fruit, 20, order.getId().toString()).build();
        assertTrue(order.addOrderItem(item));

        assertTrue(order.getNumberItems() == 1);
        assertEquals(order.getOrderAmount().toString(), ("4.00"));

        Set<OrderItem> items = order.getOrderItems();
        assertTrue(items.size() == 1);
        Iterator<OrderItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            OrderItem current = iterator.next();
            assertEquals(item.getFruitType().getName(), current.getFruitType().getName());
            assertEquals(item.getFruitType().getId(), current.getFruitType().getId());
            assertEquals(item.getIdOrder(), current.getIdOrder());
            assertEquals(item.getQuantity(), current.getQuantity());
        }

        assertTrue(order.removeOrderItem(order.getId().toString(), item.getFruitType().getId().toString()));
    }

    @Test
    public void testNullAmount() {

        boolean result = false;
        try {
            new OrderImpl.Builder(new Integer(11).toString(), new Date(), "2").amount(null).build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("amount < 0:null")) {
                result = true;
            }
        }
        assertTrue(result);
    }

    @Test
    public void testNullDate() {

        boolean result = false;
        try {
            new OrderImpl.Builder(new Integer(11).toString(), null, "2").amount(new BigDecimal("5000")).build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("dateOrder not correct")) {
                result = true;
            }
        }
        assertTrue(result);
    }

    @Test
    public void testNullIdCustomer() {

        boolean result = false;
        try {
            new OrderImpl.Builder(new Integer(11).toString(), new Date(), null).amount(new BigDecimal("5000")).build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("id argument null")) {
                result = true;
            }
        }
        assertTrue(result);
    }

    @Test
    public void testWrongAmount() {

        boolean result = false;
        try {
            new OrderImpl.Builder(new Integer(11).toString(), new Date(), "2").amount(new BigDecimal("-1.0")).build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("amount < 0:-1.0")) {
                result = true;
            }
        }
        assertTrue(result);
    }

    @Test
    public void testWrongDate() {

        boolean result = false;
        try {
            new OrderImpl.Builder(new Integer(11).toString(), null, "2").amount(new BigDecimal("4000")).build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("dateOrder not correct")) {
                result = true;
            }
        }
        assertTrue(result);
    }
}
