package io.sskuratov.sodiumconsumptioncalc;

import java.io.File;
import java.net.URL;

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
}
