package it.freshfruits.domain.vo;

import static org.junit.Assert.assertEquals;
import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.OrderItemImpl;

import java.math.BigDecimal;

import org.junit.Test;

public class OrderItemUnitTest {

    @Test
    public void testConstructorCorrect() {
        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();
        OrderItem item = new OrderItemImpl.Builder(fruit, 20, "1").build();

        assertEquals(item.getFruitType(), fruit);
        assertEquals(item.getAmountItem().toString(), "4.00");
        assertEquals(item.getQuantity(), new Integer(20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFruitType() {
        new OrderItemImpl.Builder(null, 20, "1").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullQuantity() {

        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();
        new OrderItemImpl.Builder(fruit, null, "1").build();
    }
}
