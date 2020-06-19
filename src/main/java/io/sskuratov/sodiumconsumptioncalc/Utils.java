package io.sskuratov.sodiumconsumptioncalc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class Utils {

    private final static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static Utils get() {
        return new Utils();
    }

    public String getStringFromResource(String fileName) {
        StringBuilder builder = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        }

        File file = new File(resource.getFile());
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return builder.toString();
    }
}
