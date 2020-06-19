package io.sskuratov.sodiumconsumptioncalc;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SodiumConsumptionCalcApplication {

    static {
        ApiContextInitializer.init();
    }

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new CalcBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}