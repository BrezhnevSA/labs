package ifmo.ws.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ifmo_training";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASS = "postgres";

    static{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
