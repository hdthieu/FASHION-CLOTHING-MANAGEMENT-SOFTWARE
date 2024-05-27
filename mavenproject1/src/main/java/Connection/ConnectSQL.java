package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
    public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=quanlybanhang;encrypt=true;trustServerCertificate=true;";
    public static final String USER = "sa";
    public static final String PASSWORD = "123";

    public Connection con = null;
    public static ConnectSQL instance = new ConnectSQL();

    public ConnectSQL() {
        connect();
    }

    public static ConnectSQL getInstance() {
        return instance;
    }

    public void connect() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        disconnect();
    }
}
