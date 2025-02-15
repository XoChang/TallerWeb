package Group3.JobsMadeEasy.database.dao;

import Group3.JobsMadeEasy.database.DatabaseConstant;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: it will set up database connection for all apis
 */
@Configuration
public class DatabaseSetup extends DatabaseConstant implements IDatabaseSetup {

    protected static final String DATABASE_CONFIGURATION_PROPERTIES_FILE =
            DatabaseConstant.DATABASE_CONFIGURATION_PROPERTIES_FILE;

    protected static DatabaseSetup databaseSetup;

    protected Connection connection;

    public static DatabaseSetup getConnectionInstance() {
        if (databaseSetup == null) {
            databaseSetup = new DatabaseSetup();
        }
        return databaseSetup;
    }

    /**
     *
     * @return configuration from file
     * @throws IOException
     */
    public static Properties getProperties() throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = DatabaseSetup.class
                .getClassLoader()
                .getResourceAsStream(DATABASE_CONFIGURATION_PROPERTIES_FILE);
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }

    /**
     *
     * @return connection object
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public Connection getConnectionObject() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = DatabaseSetup.getConnectionInstance().getProperties();
        Class.forName(properties.getProperty("spring.datasource.driverClassName"));
        final String url = properties.getProperty("spring.datasource.url");
        final String userName = properties.getProperty("spring.datasource.username");
        final String userPassword = properties.getProperty("spring.datasource.password");
        return DriverManager.getConnection(url, userName, userPassword);
    }

    /**
     * @description: it will close all connections at the end of api call
     */
    @Override
    public void closeDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            connection = null;
        }
    }
}
