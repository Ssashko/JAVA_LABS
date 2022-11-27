package lab5;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestLab5 {
    @DataProvider
    public Object[][] testConnectionProvider() {

        return new Object[][] {
                {"??????","postgres","root"},
                {"jdbc:postgresql://localhost:5432/java","????","root"},
                {"jdbc:postgresql://localhost:5432/java","postgres","????"},
        };
    }
    @Test(dataProvider = "testConnectionProvider", expectedExceptions = SQLException.class)
    public void testConnection(String url, String name, String pass) throws SQLException {
        DBService test = new DBService(url,name,pass);
        test.getConnection();
    }
    @Test
    public void testTableCreation() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");
        test.createTables();
    }
    @Test
    public void testTableErasure() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");
        test.deleteTables();
    }
}