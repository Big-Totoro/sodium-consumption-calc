package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UrinePotassiumConcentrationCommand extends AbstractCommand {

    public UrinePotassiumConcentrationCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Шаг 3 из 7: Далее, введите параметр: \"" +
                        "Концентрация калия в разовой порции мочи (ммоль/л)" +
                        "\"");
    }
}