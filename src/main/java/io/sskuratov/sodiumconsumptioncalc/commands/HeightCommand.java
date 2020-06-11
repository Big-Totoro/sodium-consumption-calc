package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HeightCommand extends AbstractCommand {

    public HeightCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Шаг 6 из 7: Далее, введите параметр: \"" +
                        "Рост (см)" +
                        "\"");
    }
}

