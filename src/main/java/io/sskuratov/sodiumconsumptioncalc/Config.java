package io.sskuratov.sodiumconsumptioncalc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String MONGO_DB_URL;
    public static String DATABASE_NAME;
    public static Properties STRINGS; // access properties by STRINGS.getProperty("PROPERTY")

    private final static Logger logger = LoggerFactory.getLogger(Config.class);

    static {
        Properties propConfig = new Properties();
        STRINGS = new Properties();
        InputStream input = null;

        try {
            String configProperties = "config.properties";

            input = Config.class.getClassLoader().getResourceAsStream(configProperties);
            if (input == null) {
                logger.debug("Sorry, unable to find " + configProperties);
            } else {
                propConfig.load(input);

                logger.info("Got config: " + propConfig.stringPropertyNames());

                MONGO_DB_URL = propConfig.getProperty("MONGO_DB_URL");
                DATABASE_NAME = propConfig.getProperty("DATABASE_NAME");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public static String getString(String propertyName) {
        return STRINGS.getProperty(propertyName, "<error>");
    }
}
