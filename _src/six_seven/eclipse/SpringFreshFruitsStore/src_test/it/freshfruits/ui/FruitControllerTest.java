package it.freshfruits.ui;

import static org.junit.Assert.assertEquals;
import it.freshfruits.SpringFactory;
import it.freshfruits.conf.dbunit.DBFruitType;
import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.vo.FruitMap;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;

public class FruitControllerTest {

    private FruitController fruitController;

    private ApplicationContext ctx;

    @Before
    public void setup() {
        ctx = SpringFactory.getXmlWebApplicationContext();
        fruitController = (FruitController) ctx.getBean("fruitController");

    }

    @After
    public void tearDown() {
        fruitController = null;
    }

    @Test
    public void setupForm() {
        assertEquals(fruitController.setupForm(null, new ModelMap()), "role/form");
        DBFruitType dbFruit = new DBFruitType();
        dbFruit.prepareDb();
        assertEquals(fruitController.setupForm(2, new ModelMap()), "role/form");
        dbFruit.cleanDb();
    }

    @Test
    public void processSubmit() {
        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(10), new BigDecimal("0.30")).color("orange").flavour("sweet").location("sardinia").build();
        FruitMap map = new FruitMap(fruit);
        BindingResult result = new BeanPropertyBindingResult(fruit, "fruit");
        SessionStatus status = new SimpleSessionStatus();
        assertEquals(fruitController.processSubmit(map, result, status), "redirect:role.list.admin");

    }
}
