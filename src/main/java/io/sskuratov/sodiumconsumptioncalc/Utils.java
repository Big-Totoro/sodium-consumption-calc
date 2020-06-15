package io.sskuratov.sodiumconsumptioncalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Objects;

public class Utils {

    public static Utils get() {
        return new Utils();
    }

    public File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public String getStringFromResource(String fileName) {
        Objects.requireNonNull(fileName);

        StringBuilder builder = new StringBuilder();

        try (FileReader reader = new FileReader(getFileFromResources(fileName));
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
