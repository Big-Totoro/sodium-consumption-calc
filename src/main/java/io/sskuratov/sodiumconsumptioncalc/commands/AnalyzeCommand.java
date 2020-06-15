package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.Utils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        super.execute(message, Utils.get().getStringFromResource("Analyze.txt"));
    }
}
