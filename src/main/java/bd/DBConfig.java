package bd;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private static final Properties props = new Properties();

    private static String user;
    private static String password;

    public static String getUser() {
        return user;
    }

    public void setUser(String user) {
        DBConfig.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        DBConfig.password = password;
    }

    static {
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input);
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
