package data;

import logic.FrameType;

import java.sql.Connection;
import java.sql.Statement;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

class DataAccessorDatabaseTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();
        String query1 = "USE `glarmester`;";
        String query2 = "CREATE TABLE `glarmester`.`prices` (\n" +
                "  `name` VARCHAR(25) NOT NULL,\n" +
                "  `price` FLOAT NULL,\n" +
                "  PRIMARY KEY (`name`));";
        String query3 = "INSERT INTO `glarmester`.`prices` (`name`, `price`) VALUES \n" +
                "('glass', '300'), \n" +
                "('simple', '100'), \n" +
                "('ornate', '200'), \n" +
                "('lavish', '350');";
        stmt.execute(query1);
        stmt.execute(query2);
        stmt.execute(query3);
        connection.close();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() throws Exception {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();
        String query1 = "DROP TABLE `prices`;";
        stmt.execute(query1);
        connection.close();
    }

    @org.junit.jupiter.api.Test
    void getGlassprice() {
        DataAccessorDatabase dad = new DataAccessorDatabase();
        long time = System.currentTimeMillis();
        double glassprice = dad.getGlassprice();
        long end = System.currentTimeMillis();
        assertThat(end-time,is(lessThan(100L)));
        assertThat(glassprice, is(300.0));
    }

    @org.junit.jupiter.api.Test
    void getFramePrice() {
        DataAccessorDatabase dad = new DataAccessorDatabase();
        long time = System.currentTimeMillis();
        double glassprice = dad.getFramePrice(FrameType.Lavish);
        long end = System.currentTimeMillis();
        assertThat(end-time,is(lessThan(100L)));
        assertThat(glassprice, is(350.0));
    }
}