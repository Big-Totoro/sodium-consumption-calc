package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.Utils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AnalyzeCommand extends AbstractCommand {
    /**
     * Constructor
     *
     * @param calcBot Telegram bot
     */
    public AnalyzeCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        File file = Utils.get().getFileFromResources("Analyze.txt");
        StringBuilder builder = new StringBuilder();

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        super.execute(message, builder.toString());
    }

}
