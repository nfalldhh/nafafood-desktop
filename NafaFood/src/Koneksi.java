import java.sql.*;

public class Koneksi {
    final String DRIVER = "com.mysql.jdbc.Driver";
    final String DB_PATH = "jdbc:mysql://localhost/db_nafafood";
    final String USER = "root";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    public void connect() throws SQLException, Exception {
        Class.forName(DRIVER);
        System.out.println("Connecting..");
        conn = DriverManager.getConnection(DB_PATH, USER, PASS);
        stmt = conn.createStatement();
    }

    public void closeConnection() throws SQLException, Exception {
        stmt.close();
        conn.close();
    }

    public Statement getStatement() {
        System.out.println("Request..");
        return stmt;
    }

    public Connection getConnection() {
        return conn;
    }
}
