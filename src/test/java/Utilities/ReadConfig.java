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

    public void setLoginEmail(String value) {

        properties.setProperty("LOGIN_EMAIL", value);

    }

    public void setLoginPassword(String value) {

        properties.setProperty("LOGIN_PASSWORD", value);

    }

    public void saveConfig() throws IOException {

        File src = new File("src/test/java/Properties/config.properties");

        FileOutputStream fos = new FileOutputStream(src);

        properties.store(fos, null);

    }

}
