package it.freshfruits;

import it.freshfruits.conf.dbunit.DbUnit;
import it.freshfruits.domain.entity.CustomerUnitTest;
import it.freshfruits.domain.entity.FruitTypeUnitTest;
import it.freshfruits.domain.entity.OrderUnitTest;
import it.freshfruits.domain.factory.CustomerFactoryTest;
import it.freshfruits.domain.repository.CustomerRepositoryTest;
import it.freshfruits.domain.repository.OrderRepositoryTest;
import it.freshfruits.domain.service.SupplyServiceTest;
import it.freshfruits.domain.vo.AddressUnitTest;
import it.freshfruits.domain.vo.ContactInformationUnitTest;
import it.freshfruits.domain.vo.OrderItemUnitTest;
import it.freshfruits.ui.CustomerControllerTest;
import it.freshfruits.ui.FruitControllerTest;
import it.freshfruits.utils.ValidationUtilsTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { AddressUnitTest.class, ContactInformationUnitTest.class, CustomerUnitTest.class, FruitTypeUnitTest.class, ValidationUtilsTest.class, OrderUnitTest.class, OrderItemUnitTest.class,
        CustomerRepositoryTest.class, OrderRepositoryTest.class, CustomerFactoryTest.class, CustomerControllerTest.class, FruitControllerTest.class, SupplyServiceTest.class })
public class AllTests {

    @BeforeClass
    public static void setUp() throws Exception {
        SpringFactory.setUpXmlWebApplicationContext();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        DbUnit.closeConnection();
        SpringFactory.destroyXmlWebApplicationContext();
    }
}
