package Utilities;

import java.io.*;
import java.util.Properties;

public class ReadConfig {

    private Properties properties;

    public ReadConfig() throws IOException {

        File src = new File("src/test/java/Properties/config.properties");
        FileInputStream fis = new FileInputStream(src);

        properties = new Properties();
        properties.load(fis);

    }

    public String getBrowser() {

        return properties.getProperty("BROWSER");

    }

    public String getBaseUrl() {

        return properties.getProperty("BASE_URL");

    }

    public String getLoginEmail() {

        return properties.getProperty("LOGIN_EMAIL");

    }

    public String getLoginPassword() {

        return properties.getProperty("LOGIN_PASSWORD");

    }

    public String getFirstName() {

        return properties.getProperty("FIRST_NAME");

    }

    public String getLastName() {

        return properties.getProperty("LAST_NAME");

    }

    public void setLoginEmail(String value) {

        properties.setProperty("LOGIN_EMAIL", value);

    }

    public void setLoginPassword(String value) {

        properties.setProperty("LOGIN_PASSWORD", value);

    }

    public void setFirstName(String value) {

        properties.setProperty("FIRST_NAME", value);

    }

    public void setLastName(String value) {

        properties.setProperty("LAST_NAME", value);

    }

    public void saveConfig() throws IOException {

        File src = new File("src/test/java/Properties/config.properties");

        FileOutputStream fos = new FileOutputStream(src);

        properties.store(fos, null);

    }

}
