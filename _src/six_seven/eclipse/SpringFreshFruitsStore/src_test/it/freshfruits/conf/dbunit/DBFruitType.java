package it.freshfruits.conf.dbunit;

import java.io.FileInputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DBFruitType extends DbUnit {

    public IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(new FileInputStream("src_test/it/freshfruits/conf/dbunit/fruittype-test.xml"));
    }
}
