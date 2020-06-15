package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.Utils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CompletedCommand extends AbstractCommand {

    public CompletedCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message, String text) throws TelegramApiException {
        StringBuilder builder = new StringBuilder();

        builder.append(
                "Результат вычисления, потребление соли: <b>" +
                text +
                "</b> грамм в сутки." +
                System.lineSeparator()
        );
        builder.append(Utils.get().getStringFromResource("Results.txt"));

        super.execute(message, builder.toString());
    }
}