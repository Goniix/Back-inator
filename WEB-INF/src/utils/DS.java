package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DS {
    private Connection con;

    public DS() {
        try {
            Properties p = new Properties();
            // De base le working dir de catalina c'est le tomcat/bin
            p.load(new FileInputStream(
                    "/home/infoetu/gabriel.redouin-innecco.etu/tomcat/webapps/tp321/WEB-INF/src/utils/config.prop"));
            String url = p.getProperty("url");
            String login = p.getProperty("login");
            String password = p.getProperty("password");
            Class.forName(p.getProperty("driver"));
            con = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
