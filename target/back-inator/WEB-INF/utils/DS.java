package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DS {
    private Connection con;

    private static final String DEFAULT_CONFIG = System.getProperty("catalina.base")
            + "/webapps/Back-inator/WEB-INF/src/utils/config/maisonConfig.postgres.prop";

    private static DS instance;

    private DS() {
        System.out.println("DS: loading DEFAULT_CONFIG...");
        Properties config = importConfigFile(DEFAULT_CONFIG);
        connect(config);
    }

    public Properties importConfigFile(String filePath) {
        System.out.println("DS: importing config: " + filePath);

        Properties config = new Properties();
        try {
            config.load(new FileInputStream(filePath));
            System.out.println("DS: found config: " + filePath);
        } catch (IOException e) {
            System.out.println("DS: Config file not found : " + e.getMessage());
        }
        return config;
    }

    public boolean connect(Properties config) {
        String url = config.getProperty("url");
        String login = config.getProperty("login");
        String password = config.getProperty("password");
        String sqlDriver = config.getProperty("driver");
        return connect(url, login, password, sqlDriver);
    }

    public boolean connect(String url, String login, String password, String driver) {
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, login, password);
            System.out.println("DS: Connected to database!");
            return true;
        } catch (Exception e) {
            System.out.println("DS: could not connect: " + e.getMessage());
            return false;
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

    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
