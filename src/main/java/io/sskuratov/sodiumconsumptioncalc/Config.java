package io.sskuratov.sodiumconsumptioncalc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String REDIS_DB_URL;
    public static String DATABASE_NAME;

    private final static Logger logger = LoggerFactory.getLogger(Config.class);

    static {
        Properties propConfig = new Properties();
        InputStream input = null;

        try {
            String configProperties = "config.properties";

            input = Config.class.getClassLoader().getResourceAsStream(configProperties);
            if (input == null) {
                logger.debug("Sorry, unable to find " + configProperties);
            } else {
                propConfig.load(input);

                logger.info("Got config: " + propConfig.stringPropertyNames());

                REDIS_DB_URL = propConfig.getProperty("REDIS_DB_URL");
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
}
