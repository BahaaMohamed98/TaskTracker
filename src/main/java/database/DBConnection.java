package database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private final static String URL = "jdbc:h2:./database;AUTO_SERVER=TRUE";
    private final static HikariDataSource dataSource = createDataSource();

    private static HikariDataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        return ds;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
