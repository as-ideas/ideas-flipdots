package de.axelspringer.ideas.flipdots;

import java.io.FileInputStream;
import java.util.Properties;

public class FlipdotProperties {

    public static final FlipdotProperties INSTANCE = new FlipdotProperties();

    private Properties properties;

    public String getDataDogApiKey() {
        return getProperty("de.axelspringer.ideas.flipdots.datadog.api.key");
    }


    public String getDataDogAppKey() {
        return getProperty("de.axelspringer.ideas.flipdots.datadog.app.key");
    }


    public String getDataDogQueryLogins() {
        return getProperty("de.axelspringer.ideas.flipdots.datadog.query.logins");
    }


    public String getDataDogQuerySignups() {
        return getProperty("de.axelspringer.ideas.flipdots.datadog.query.signups");
    }

    private String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream file = new FileInputStream("./pcp.properties");
                properties.load(file);
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return properties;
    }
}
