package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CompletedCommand extends AbstractCommand {

    public CompletedCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message, String text) throws TelegramApiException {
        super.execute(message,
                "Результат вычисления, потребление соли: " +
                        text +
                        " грамм в сутки.");
    }
}
