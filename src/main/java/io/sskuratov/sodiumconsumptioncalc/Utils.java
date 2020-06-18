package io.sskuratov.sodiumconsumptioncalc;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

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
        String lines = "";
        ClassPathResource resource = new ClassPathResource(fileName);
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            lines = bufferedInputStream.lines().collect(Collectors.joining());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
