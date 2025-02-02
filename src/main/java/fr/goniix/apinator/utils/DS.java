package fr.goniix.apinator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DS implements AutoCloseable {
    private static final String DEFAULT_CONFIG = "config/maisonConfig.postgres.prop";
    private static DS instance;
    private Connection con;

    private DS() {
        System.out.println("DS: loading DEFAULT_CONFIG...");
        try{
            Properties config = loadConfig(DEFAULT_CONFIG);
            connect(config);
        }
        catch(IOException e){
            System.out.println("DS: invalid default config: " + e.getMessage());
        }
    }

    /** Imports Properties from ressources folder (does not load to DS, must is further loaded with connect(Properties))
     * @param ressourcePath Path to the ressource from src/main/resources
     * @return Properties imported from the ressource file
     * @throws IOException If file is not found
     */
    public Properties loadConfig(String ressourcePath) throws IOException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream(ressourcePath)) {
            if (input == null) {
                throw new IOException("File not found: config/config.prop");
            }
            props.load(input);  // Load properties file
        }
        return props;
    }

    public void connect(Properties config) {
        String url = config.getProperty("url");
        String login = config.getProperty("login");
        String password = config.getProperty("password");
        String sqlDriver = config.getProperty("driver");
        connect(url, login, password, sqlDriver);
    }

    public void connect(String url, String login, String password, String driver) {
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, login, password);
            System.out.println("DS: Connected to database!");
        } catch (Exception e) {
            System.out.println("DS: could not connect: " + e.getMessage());
        }
    }

    public static DS getInstance() {
        if (instance == null) {
            instance = new DS();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Exception while closing: " + e.getMessage());
        }
    }
}
