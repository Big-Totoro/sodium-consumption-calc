package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AgeCommand extends AbstractCommand {

    public AgeCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Шаг 5 из 7: Далее, введите параметр: \"" +
                        "Возраст (полных лет)" +
                        "\"");
    }
}
