package it.freshfruits.conf.dbunit;

import it.freshfruits.conf.Config;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public abstract class DbUnit extends DatabaseTestCase {

    public DbUnit() {
    }

    public static IDatabaseConnection setUpConnection() {
        Config config = new Config();
        System.out.println("DbUnit setUpConnection");
        if (dbConn == null) {
            Connection jdbcConnection = null;
            try {
                // Class.forName(config.getDbDriver());
                Class.forName(config.getDbDriverProduction());
                // Class.forName(config.getDbDriverDebug());
                jdbcConnection = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPass());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new DatabaseConnection(jdbcConnection);
        } else {
            return dbConn;
        }
    }

    public IDatabaseConnection getConnection() throws Exception {

        if (null == dbConn) {
            dbConn = setUpConnection();
        }
        return dbConn;
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        Class.forName(config.getDbDriver());
        Connection jdbcConnection = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPass());
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // dtd
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("src_test/it/freshfruits/conf/dbunit/database-schema.dtd"));

        // export dati
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("src_test/it/freshfruits/conf/dbunit/full.xml"));
    }

    /*
     * public DatabaseOperation getSetUpOperation() throws Exception { return DatabaseOperation.REFRESH; }
     */

    /*
     * public DatabaseOperation getTearDownOperation() throws Exception { return DatabaseOperation.DELETE; }
     */

    public void prepareDb() {
        try {
            try {
                DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
            } finally {
                // connection.close();
            }
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ++operation;
    }

    public void cleanDb() {
        try {

            try {
                DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
            } finally {
                // connection.close();
            }
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ++operation;
    }

    public static void closeConnection() {
        System.out.println("DbUnit closeConnection");
        if (dbConn != null) {
            try {
                dbConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConn = null;
                System.gc();
            }
        }
        System.out.println("Total DbUnit Operation:" + operation);
    }

    private static IDatabaseConnection dbConn = setUpConnection();
    private static int operation;
}
