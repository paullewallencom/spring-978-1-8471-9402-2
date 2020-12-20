package it.freshfruits.domain.service;

import static org.junit.Assert.assertTrue;
import it.freshfruits.SpringFactory;
import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.OrderItemImpl;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class SupplyServiceTest {

    @Before
    public void setUp() throws Exception {
        ctx = SpringFactory.getXmlWebApplicationContext();
        supplyService = (SupplyService) ctx.getBean("supplyService");
    }

    @Test
    public void testIAvailable() {

        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();
        OrderItem item = new OrderItemImpl.Builder(fruit, 400, "1").build();

        supplyService.init();
        supplyService.release("1", item.getFruitType().getId().toString());
        OrderItem itemThree = new OrderItemImpl.Builder(fruit, 300, "1").build();

        assertTrue(supplyService.isAvailable(itemThree));
    }

    @Test
    public void testRetain() {

        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();
        OrderItem item = new OrderItemImpl.Builder(fruit, 400, "1").build();

        supplyService.init();
        supplyService.release("1", item.getFruitType().getId().toString());

        OrderItem itemFour = new OrderItemImpl.Builder(fruit, 200, "1").build();

        assertTrue(supplyService.isAvailable(itemFour));
        assertTrue(supplyService.retainItem(itemFour));

    }

    @Test
    public void testRelease() {

        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();

        supplyService.init();

        OrderItem itemRetain = new OrderItemImpl.Builder(fruit, 200, "1").build();
        assertTrue(supplyService.retainItem(itemRetain));

        assertTrue(supplyService.release("1", itemRetain.getFruitType().getId().toString()));

        OrderItem itemFour = new OrderItemImpl.Builder(fruit, 200, "1").build();

        assertTrue(supplyService.isAvailable(itemFour));
        assertTrue(supplyService.retainItem(itemFour));
        assertTrue(supplyService.release(itemFour.getIdOrder().toString(), itemFour.getFruitType().getId().toString()));
    }

    @After
    public void tearDown() throws Exception {
        ctx = null;
        supplyService = null;
    }

    private SupplyService supplyService;
    private ApplicationContext ctx;
}
