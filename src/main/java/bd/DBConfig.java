package bd;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    
    //en esta clase se le da el usuario y extrae del archivo secreto el url de la bd

    private static final Properties props = new Properties();
    
    private static String user;
    private static String pasword;

    public static String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    static {
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
    
    
}
