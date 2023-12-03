package artdealer.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;

public class ConnectDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/artshop";
    static final String USER = "root";
    static final String PASSWORD = "aggY836^@zT9&";

    public static Connection ConnectDb() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return connection;

        // HikariConfig config = new HikariConfig();
        // config.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
        // config.setUsername("bart");
        // config.setPassword("51mp50n");
        // config.addDataSourceProperty("cachePrepStmts", "true");
        // config.addDataSourceProperty("prepStmtCacheSize", "250");
        // config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // HikariDataSource ds = new HikariDataSource(config);
    }

    
}
