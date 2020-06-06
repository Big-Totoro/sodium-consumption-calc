package io.sskuratov.sodiumconsumptioncalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class SodiumConsumptionCalcApplication {

    static {
        ApiContextInitializer.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(SodiumConsumptionCalcApplication.class, args);
    }
}