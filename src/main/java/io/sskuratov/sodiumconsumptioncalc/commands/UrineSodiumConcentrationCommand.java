package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UrineSodiumConcentrationCommand extends AbstractCommand {

    public UrineSodiumConcentrationCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Шаг 2 из 7: Далее, введите параметр: \"" +
                        "Концентрация натрия в разовой порции мочи (ммоль/л)" +
                        "\"");
    }
}
